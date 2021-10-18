package com.jesusrojo.launcher


import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jesusrojo.launcher.databinding.LauncherFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherFragment : Fragment() {

    lateinit var _binding: LauncherFragmentLayoutBinding
    private val binding get() = _binding!! // property only valid between onCreateView and onDestroyView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LauncherFragmentLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi(){
        binding.startGameBtn.setOnClickListener {
            val uri = Uri.parse("myApp://GoToItemsFragment")
            findNavController().navigate(uri)
        }
        binding.listFragmentBtn.setOnClickListener {
            val uri = Uri.parse("myApp://GoToItemsFragment")
            findNavController().navigate(uri)
        }
    }

    // MENU TOP RIGHT
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_top_right_launcher, menu)

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_item_prefs -> {
                onClickMenuTopRightPrefs()
                true
            }
            else -> false
        }

    private fun onClickMenuTopRightPrefs() {
        findNavController().navigate(R.id.go_to_PrefsFragment, )
    }
}