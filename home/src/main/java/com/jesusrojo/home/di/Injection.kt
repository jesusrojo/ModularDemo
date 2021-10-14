package com.jesusrojo.home.di


import android.app.Activity
import com.jesusrojo.data.model.UiData
import com.jesusrojo.home.databinding.ItemsLayoutBinding
import com.jesusrojo.home.presentation.adapter.UiDataListAdapter
import com.jesusrojo.home.presentation.fragment.items.uihelp.UiHelper

class Injection {

    companion object {

        //LIST ADAPTER WITH DIFF
        fun provideUiDataListAdapter(
            values: ArrayList<UiData>,
            listener: (UiData, Int) -> Unit,
            listenerMenu: (UiData, Int) -> Unit
        ): UiDataListAdapter = UiDataListAdapter(listener, listenerMenu)


//        //ADAPTER
//        fun provideUiDataAdapter(
//            values: ArrayList<UiData>,
//            listener: (UiData, Int) -> Unit,
//            listenerMenu: (UiData, Int) -> Unit
//        ): UiDataAdapter = UiDataAdapter(values, listener, listenerMenu)


        //
        fun provideUiHelper(
            activity: Activity,
            _binding: ItemsLayoutBinding?,
            onClickAdapterItem: (UiData, Int) -> Unit,
            fetchQuery: (String) -> Unit,
            refreshDatas: () -> Unit,
            fetchNextDatas: () -> Unit,
            deleteAll: () -> Unit,
            onClickMenuTopRight01: () -> Unit,
        ): UiHelper =
            UiHelper(activity, _binding,
                { uiData, position -> onClickAdapterItem(uiData, position) },
                { query -> fetchQuery(query) },
                { refreshDatas() },
                { fetchNextDatas() },
                { deleteAll() },
                { onClickMenuTopRight01() })
    }
}