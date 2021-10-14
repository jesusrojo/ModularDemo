package com.jesusrojo.home.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.data.model.UiData
import com.jesusrojo.home.databinding.ItemLayoutBinding

class UiDataViewHolder(
    private val binding: ItemLayoutBinding,
    private val listener: (UiData, Int) -> Unit,
    private val listenerMenu: (UiData, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMyData(uiData: UiData, position: Int) {

        binding.cardViewContainerItem.setOnClickListener { listener(uiData, position) }
        binding.menuIndividualTvItem.setOnClickListener { listenerMenu(uiData, position) }

        var textUi = position.toString()
        binding.positionTvItem.text = textUi // _up DEBUG POSITION

        textUi = uiData.name
        binding.titleTvItem.text = textUi

        textUi = uiData.toString() // _UP DEBUG TO STRING
//        textUi = uiData.description
        binding.descriptionTvItem.text = textUi
//
//        textUi = uiData.content
//        binding.contentTvItem.text = textUi
//
//        textUi =  uiData.author
//        binding.authorNameTvItem.text = textUi

//        textUi =  formatNumber(uiData.starsCount.toInt())
//        binding.startsCountTvItem.text = textUi

        // AVATAR
//        val avatarUrl = uiData.urlToImage
//        loadImageSquareGlide(binding.avatarIvItem, avatarUrl)
    }
}