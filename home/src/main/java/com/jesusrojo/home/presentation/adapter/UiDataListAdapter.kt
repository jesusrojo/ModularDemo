package com.jesusrojo.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jesusrojo.data.model.UiData
import com.jesusrojo.home.databinding.ItemLayoutBinding


class UiDataListAdapter(
    private val listener: (UiData, Int) -> Unit,
    private val listenerMenu: (UiData, Int) -> Unit
):  ListAdapter<UiData, UiDataViewHolder>(UiDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UiDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemLayoutBinding = ItemLayoutBinding.inflate(
            layoutInflater, parent, false)
        return UiDataViewHolder(binding, listener, listenerMenu)
    }

    override fun onBindViewHolder(holder: UiDataViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bindMyData(data,position) }
    }


    fun setNewValues(newValues: ArrayList<UiData>) {}//nothing,
    // is just for when we change the adapter.  WE use submitList

    fun deleteAll() { submitList(emptyList()) }

    // SORT
    fun orderByTitle() {
        val values = currentList
        val modifiableList: ArrayList<UiData> = ArrayList(values)
        modifiableList.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.name }))
        submitList(values)
    }

    fun orderByAuthorName() {
        val values = currentList
        val modifiableList: ArrayList<UiData> = ArrayList(values)
     //todo   modifiableList.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.author }))
        submitList(modifiableList)
    }

    fun orderByStars() {
        val values = currentList
        val modifiableList: ArrayList<UiData> = ArrayList(values)
    //todo    modifiableList.sortBy { it.id }
        values.reverse()
        submitList(modifiableList)
    }
}

class UiDataDiffCallback : DiffUtil.ItemCallback<UiData>() {
    override fun areItemsTheSame(oldItem: UiData, newItem: UiData): Boolean {
        return oldItem.dataId == newItem.dataId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: UiData, newItem: UiData): Boolean {
        return oldItem == newItem
    }
}