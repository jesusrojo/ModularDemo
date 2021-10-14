package com.jesusrojo.home.presentation.fragment.items.uihelp

import android.app.Activity
import android.widget.PopupMenu
import android.widget.TextView
import com.jesusrojo.home.R

class PopupMenuUtil {
    companion object {

        fun createNewMenuPopup(activity: Activity, view: TextView): PopupMenu {
            val popupMenu = PopupMenu(activity, view)
            val languages = activity.resources.getStringArray(R.array.languages_array)
            addItemsPopUpMenu(popupMenu, languages)
            return popupMenu
        }

        private fun addItemsPopUpMenu(popupMenu: PopupMenu,
                                      languages: Array<String>) {
            languages.forEach { popupMenu.menu.add(it) }
        }


//    // SPINNER
//    private fun initSpinner() {
//        val spinner: Spinner = binding.spinnerMain
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val languageClicked =  parent?.getItemAtPosition(position)
//                DebugHelp.l("spinner $languageClicked")
//                languageClicked?.let {
//                    fetchQuery(languageClicked as String)
//                }
//            }
//        }
//
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.country_array,
//            R.layout.spinner_item_layout // android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//            // spinner.setSelection(3) //will launch  onItemSelected
//        }
//    }

    }
}