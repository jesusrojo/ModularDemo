package com.jesusrojo.launcher


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}