package com.jesusrojo.home.presentation.fragment.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jesusrojo.data.model.UiData
import com.jesusrojo.home.databinding.DetailsLayoutBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    companion object {
        const val DETAILS_PARAM_KEY = "DETAILS_PARAM_KEY"
    }
    private var _binding: DetailsLayoutBinding? = null
    private val binding get() = _binding!! // property only valid between onCreateView & onDestroyView

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getSerializable(DETAILS_PARAM_KEY)
//        val data = args.myArg
        if (data != null) bindMyData(binding, data as UiData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindMyData(binding: DetailsLayoutBinding, data: UiData) {
        //TEXT
        val textUi = data.toString()
        binding.textViewExplanationDetails.text = textUi
        //IMG
//        val avatarUrl = data.urlToImage
//        loadImageCircleGlide(binding.textViewExplanationDetails, avatarUrl)

/////////// WEB VIEW
//        binding.webviewDetail.apply {
//            webViewClient = WebViewClient()
//            data?.url?.let {
//                uiData = data
//                loadUrl(it)
//            }
//        }
///////////
    }

/////////////////////////////

    //MENU
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_top_right_second, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem) =
//        when (item.itemId) {
//            R.id.menu_item_save -> {
//                saveToDB()
//                true
//            }
//            else -> false
//        }

//    private fun saveToDB() {
//        val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
//        coroutineScope.launch {
//            withContext(Dispatchers.IO) {
//
//                uiData?.let {
//                    val entyDatas = UiDataToEntyMapper.mapUiDataToEnty(uiData!!)
//                    favoritesDAO.insertOne(entyDatas)
//
//                    withContext(Dispatchers.Main){
//                        Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }
//    }

}