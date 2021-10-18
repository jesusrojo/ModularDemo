package com.jesusrojo.home.presentation.fragment.items

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.jesusrojo.common.util.DebugHelp
import com.jesusrojo.data.model.UiData
import com.jesusrojo.home.R
import com.jesusrojo.home.databinding.ItemsLayoutBinding
import com.jesusrojo.home.di.Injection
import com.jesusrojo.data.model.UiState

import com.jesusrojo.home.presentation.fragment.details.DetailsFragment
import com.jesusrojo.home.presentation.fragment.items.uihelp.UiHelper
import com.jesusrojo.common.util.exhaustive
import com.jesusrojo.common.util.toast

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class BaseUiFragment : Fragment() {

    private lateinit var uiHelper: UiHelper
    private var _binding: ItemsLayoutBinding? = null
    private val binding get() = _binding!! // property only valid between onCreateView and onDestroyView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemsLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUiHelper(savedInstanceState)
    }

    private fun initUiHelper(savedInstanceState: Bundle?) {
        //1 HERE
//        uiHelper = UiHelper(requireActivity(), binding,
//            { uiData, position -> onClickAdapterItem(uiData, position) },
//            { query -> fetchQuery(query) },
//            { refreshDatas() },
//            { fetchNextDatas() },
//            { deleteAll() },
//            { onClickMenuTopRight01() })

        // 2 INJECTION
        uiHelper = Injection.provideUiHelper(requireActivity(), binding,
            { uiData, position -> onClickAdapterItem(uiData, position) },
            { query -> fetchQuery(query) },
            { refreshDatas() },
            { fetchNextDatas() },
            { deleteAll() },
            { onClickMenuTopRight01() })
        uiHelper.create(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        uiHelper.onDestroyView()
        _binding = null
    }

    protected open fun onClickAdapterItem(uidata: UiData, position: Int) {
        val bundle = bundleOf(DetailsFragment.DETAILS_PARAM_KEY to uidata)
        findNavController().navigate(R.id.go_to_DetailsFragment, bundle)
    }

    // OPEN TO VIEW MODEL
    protected open fun fetchQuery(query: String) {}
    protected open fun refreshDatas() {}
    protected open fun fetchNextDatas() {}
    protected open fun deleteAll() {}
    protected open fun deleteAllCache() {}

    protected open fun onClickMenuTopRight01() {
        //todo   FavoritesDialogFragment.showFavoritesDialogFragment(requireActivity() as AppCompatActivity)
    }

    //UI UPDATES
    protected fun updateUiState(it: UiState<List<UiData>>) {
        when (it) {
            is UiState.Success -> {
                updateUiSuccess(it.data)
                toastMsg4Debug(it.message) //todo debug
            }
            is UiState.Loading -> updateUiLoading()
            is UiState.Error -> updateUiError(it.message)
        }.exhaustive
    }

    private fun toastMsg4Debug(msg: String?) {
        DebugHelp.l("toastMsg4Debug $msg")
        if (DebugHelp.IS_DEBUG) {
            msg?.let {
                requireActivity().toast(msg)
            }
        } else {
            //noting
        }
    }

    private fun updateUiSuccess(datas: List<UiData>?) =
        uiHelper.updateUiSuccess(datas)

    private fun updateUiError(message: String?) {
        uiHelper.updateUiError(message)
        requireActivity().toast(message!!)
    }


    private fun updateUiLoading() =
        uiHelper.updateUiLoading()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        uiHelper.onSaveInstanceState(outState)
    }

    // MENU TOP RIGHT
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        uiHelper.onCreateOptionsMenu(menu, inflater)

    override fun onOptionsItemSelected(item: MenuItem) =
        uiHelper.onOptionsItemSelected(item)
}