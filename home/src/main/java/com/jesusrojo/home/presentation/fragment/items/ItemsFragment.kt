package com.jesusrojo.home.presentation.fragment.items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jesusrojo.home.presentation.viewmodel.ItemsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : BaseUiFragment() {

    private val viewModel: ItemsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner) { updateUiState(it) }
    }

    override fun deleteAll() {
        super.deleteAll()
        viewModel.deleteAllDB()
    }
}