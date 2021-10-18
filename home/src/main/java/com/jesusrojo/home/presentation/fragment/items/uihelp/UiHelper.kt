package com.jesusrojo.home.presentation.fragment.items.uihelp

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.data.model.UiData
import com.jesusrojo.home.R
import com.jesusrojo.home.databinding.ItemsLayoutBinding
import com.jesusrojo.home.di.Injection

import com.jesusrojo.home.presentation.adapter.UiDataListAdapter
import com.jesusrojo.home.presentation.dialogfragments.DetailsDialogFragment
import com.jesusrojo.list.util.InternetHelp
import com.jesusrojo.common.util.toast


import kotlin.collections.ArrayList


class UiHelper(
    private val activity: Activity,
    private var _binding: ItemsLayoutBinding?,
    private val onClickAdapterItem: (UiData, Int) -> Unit,
    private val fetchQuery: (String) -> Unit,
    private val refreshDatas: () -> Unit,
    private val fetchNextDatas: () -> Unit,
    private val deleteAll: () -> Unit,
    private val onClickMenuTopRight01: () -> Unit,
) {
    private var positionClicked: Int = 0
    private val LAST_POSITION_PARAM_KEY = "LAST_POSITION_PARAM_KEY"
    private val LANGUAGE_PARAM_KEY = "LANGUAGE_PARAM_KEY"
    private val DEFAULT_LANGUAGE = "us"

   // private lateinit var uiDataAdapter: UiDataAdapter
    private lateinit var uiDataAdapter: UiDataListAdapter
    private val isListAdapter: Boolean = true

    private val binding get() = _binding!! // property only valid between onCreateView and onDestroyView
    private lateinit var llm: LinearLayoutManager

    fun create(savedInstanceState: Bundle?) {
        initTextViewLanguage(savedInstanceState)
        val position = savedInstanceState?.getInt(LAST_POSITION_PARAM_KEY)
        position?.let {
            positionClicked = position
        }
        initMyAdapter()
        initMyRecycler()
        checkInternetAndShowSnackBarIfIsKO()
    }

    fun onDestroyView() {
        _binding = null
    }


    // ADAPTER
    private fun initMyAdapter() {
        uiDataAdapter = Injection.provideUiDataListAdapter(ArrayList(),
            { uiData, position -> onClickAdapterItem(uiData, position) },
            { uiData, position -> onClickAdapterItemMenu(uiData, position) }
        )
    }

    // RECYCLER
    private fun initMyRecycler() {
        llm = LinearLayoutManager(activity.applicationContext)
//        llm = GridLayoutManager(activity.applicationContext,2)

        binding.swipeLayoutContainerItems.setOnRefreshListener {
            binding.swipeLayoutContainerItems.isRefreshing = false
            onRefreshAction()
        }

        binding.recyclerViewItems.apply {
            layoutManager = llm
            adapter = uiDataAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // DebugHelp.l("onScrolled $dy uiDataAdapter.itemCount ${uiDataAdapter.itemCount}")
//                    DebugHelp.l("isGoneProgressBar() ${isGoneProgressBar()} " +
//                            "lastPosition ${llm.findLastCompletelyVisibleItemPosition()} " +
//                            "lastPosition2 ${llm.findLastVisibleItemPosition()}" )
                    if (dy > 0) {
                        val childCount = uiDataAdapter.itemCount
                        //FAIL WITH NEWS
                        // val lastPosition = llm.findLastCompletelyVisibleItemPosition()
                        val lastPosition = llm.findLastVisibleItemPosition()

                        if (childCount - 1 == lastPosition && isGoneProgressBar()) {
                            onScrollToLastPositionRecyclerAction()
                        }
                    }
                }
            })
        }
    }

    private fun scrollRecyclerToPositionZero() {
        binding.recyclerViewItems.scrollToPosition(0)
    }

    //ACTIONS FROM RECYCLER
    private fun onRefreshAction() = refreshDatas()


    fun onScrollToLastPositionRecyclerAction() = fetchNextDatas()


    private fun onClickAdapterItem(data: UiData, position: Int) {
        positionClicked = position
        onClickAdapterItem.invoke(data, position)
    }

    private fun onClickAdapterItemMenu(data: UiData, position: Int) {
        //onClickAdapterMenuItem.invoke(data,position)
        DetailsDialogFragment.showInfoDialogFragment(
            activity as AppCompatActivity, data.title, data.toString()) // _UP rename
///////////////OK WITH PARCELABLE OBJECT
//        val bundle = bundleOf(SecondFragment.UIDATA_PARAM_KEY to data)
//        findNavController().navigate(R.id.action_FirstFragment_to_DetailsFragment, bundle)
/////////////
    }


    //UI UPDATES
    fun updateUiSuccess(datas: List<UiData>?) {
        hideProgressBar()
        if (datas != null) {
            if (datas.isNotEmpty()) {
                if(isListAdapter){
                    uiDataAdapter.submitList(datas)
                } else {
                    uiDataAdapter.setNewValues(ArrayList(datas))//(datas as ArrayList<UiData>)
                    scrollRecyclerToPositionZero()
                }
                hideTextViewError()
                val textUi = "Size: ${datas.size}"
                binding.tvDebugItems.text = textUi //_UP DEBUG
            } else {
                showMsgBadList()
            }
        } else {
            showMsgBadList()
        }
    }

    fun updateUiError(message: String?) {
        hideProgressBar()
        if (message != null && message.isNotEmpty()) {
            showTextViewErrorWithMessage(message)
        } else {
            hideTextViewError()
        }
    }

    fun updateUiLoading() {
        showProgressBar()
        hideTextViewError()
    }


    // PROGRESS BAR
    private fun showProgressBar() {
        binding.progressBarItems.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarItems.visibility = View.GONE
    }

    private fun isGoneProgressBar() = binding.progressBarItems.visibility == View.GONE

    // TEXT VIEW ERROR
    private fun hideTextViewError() {
        binding.textViewErrorItems.visibility = View.GONE
        binding.textViewErrorItems.text = ""
    }

    private fun showTextViewErrorWithMessage(message: String?) {
        binding.textViewErrorItems.text = message
        binding.textViewErrorItems.visibility = View.VISIBLE
    }

    private fun showMsgBadList() {
        val message = activity.getString(R.string.error_empty_list_explanation)
        binding.textViewErrorItems.text = message
        binding.textViewErrorItems.visibility = View.VISIBLE
    }

    // LANGUAGE
    private fun initTextViewLanguage(savedInstanceState: Bundle?) {
        var language = savedInstanceState?.getString(LANGUAGE_PARAM_KEY)
        if (language == null || language.isEmpty()) {
            language = DEFAULT_LANGUAGE
        }
        binding.tvLanguageItems.text = language
        binding.tvLanguageItems.setOnClickListener { showMenuPopup() }
    }


    private fun showMenuPopup() {
        val popupMenu = PopupMenuUtil.createNewMenuPopup(activity, binding.tvLanguageItems)
        popupMenu.setOnMenuItemClickListener {
            onClickPopupMenu(it)
            true
        }
        popupMenu.show()
    }

    private fun onClickPopupMenu(it: MenuItem) {
        val language = it.title
        binding.tvLanguageItems.text = language
        fetchQuery(language as String)
    }

    fun onSaveInstanceState(outState: Bundle) {
        val language = binding.tvLanguageItems.text.toString()
        outState.putString(LANGUAGE_PARAM_KEY, language)
        outState.putInt(LAST_POSITION_PARAM_KEY, positionClicked)
    }

    // INTERNET
    private fun checkInternetAndShowSnackBarIfIsKO() {
        InternetHelp.checkInternetAndShowSnackBarIfIsKO(activity)
    }

    private fun isNetworkNotAvailableShowMessage(): Boolean {
        ////TODO  HERE OR IN VIEW MODEL?
        //// viewModel.hasInternet = isNetworkNotAvailable()
        if (!InternetHelp.isNetworkNotAvailableShowMessage(activity)) {
            return true
        }
        return false
    }

    // OPEN TO VIEW MODEL
    private fun fetchQuery(query: String) {
        activity.toast("fetchQuery $query")
        fetchQuery.invoke(query)
    }

    private fun refreshDatas() {
        if (isNetworkNotAvailableShowMessage()) return
        activity.toast("refreshDatas")
        refreshDatas.invoke()
    }

    private fun fetchNextDatas() {
        if (isNetworkNotAvailableShowMessage()) return
        activity.toast("fetchNextDatas")
        fetchNextDatas.invoke()
    }

    private fun deleteAll() {
        activity.toast("deleteAll")
        uiDataAdapter.deleteAll()
        deleteAll.invoke()
    }

    private fun deleteAllCache() {
        activity.toast("deleteAllCache")
        uiDataAdapter.deleteAll()
    }


    // SORT
    fun orderByTitle() {
        uiDataAdapter.orderByTitle()
        scrollRecyclerToPositionZero()
    }

    fun orderByAuthorName() {
        uiDataAdapter.orderByAuthorName()
        scrollRecyclerToPositionZero()

    }

    fun orderByStars() {
        uiDataAdapter.orderByStars()
        scrollRecyclerToPositionZero()

    }

    fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top_right_main, menu)
        initSearchMenu(menu)
    }

    private fun initSearchMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.menu_item_1_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    scrollRecyclerToPositionZero()
                    fetchQuery(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean { return true }
        })
    }

    fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            //// R.id.menu_item_search ->   true
            R.id.menu_item_1 -> {
                onClickMenuTopRight01.invoke()
                true
            }
            R.id.menu_item_10 -> {
                uiDataAdapter.orderByAuthorName()
                true
            }
            R.id.menu_item_11 -> {
                uiDataAdapter.orderByTitle()
                true
            }
            R.id.menu_item_12 -> {
                uiDataAdapter.orderByStars()
                true
            }

            R.id.menu_item_20 -> {
                deleteAll()
                true
            }
            R.id.menu_item_21 -> {
                deleteAllCache()
                true
            }
            R.id.menu_item_30 -> {
              //  activity.showAlertDialog(DebugHelp.fullLog, R.string.cancel)
                true
            }
            R.id.menu_item_31 -> {
             //   DebugHelp.fullLog = ""
                true
            }
            R.id.menu_item_40 -> {
             //   activity.makeIntentTo<MainActivity>()
                true
            }
            R.id.menu_item_41 -> {
//                requireActivity().startMyActivity<MainDrawerActivity>()
                true
            }
            else -> false
        }
}