package com.jesusrojo.home.presentation.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.jesusrojo.home.R
import com.jesusrojo.home.databinding.DetailsLayoutBinding

class DetailsDialogFragment : BaseDialogFragment() {

    private lateinit var binding: DetailsLayoutBinding
    private var avatarUrl = ""
    private var details = ""

    private fun initAllArguments() {
        val arguments = arguments
        if (arguments != null) {
            avatarUrl = arguments.getString(ARG_AVATAR_URL)!!
            details = arguments.getString(ARG_DETAILS)!!
        }
    }

    @MainThread
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layoutInflater = LayoutInflater.from(context)
        binding = DetailsLayoutBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllArguments()//  1 WITH ARGUMENTS
        initUi()
///////////////2 OK WITH PARCELABLE OBJECT
//        val data =  arguments?.getParcelable<UiData>(SecondFragment.UIDATA_PARAM_KEY)
//        if (data != null) bindMyData(binding, data)
///////////////
    }

    private fun initUi() {
        binding.imageButtonCancelDetails.visibility = View.VISIBLE
        binding.imageButtonCancelDetails.setOnClickListener { dismissMyDialog() }

///////// 1 WITH ARGUMENTS
        val textUi = fullText
        binding.textViewExplanationDetails.text = textUi
       // loadImageCircleGlide(binding.textViewExplanationDetails, avatarUrl)
////////
  }
////////////2 OK WITH PARCELABLE OBJECT
//    private fun bindMyData(binding: DetailsLayoutBinding, data: UiData) {
//        binding.imageButtonCancelDetails.visibility = View.GONE //this is for the DialogFragment
//
//        // TEXT EXPLANATION
//        val textUi = data.toString()
//        binding.textViewExplanationDetails.text = textUi
//
//        // AVATAR
//        val avatarUrl = data.urlToImage // _UP
//        loadImageCircleGlide(binding.textViewExplanationDetails, avatarUrl)
//    }
///////////////


    private val fullText: String
        get() {
            val activity = activity ?: return ""
            val resources = activity.resources
            val sb = StringBuilder()
            sb.append("*** ")
                .append(resources.getString(R.string.details))
                .append(" ***\n\n")
                .append(details)
                .append("\n")
            return sb.toString()
        }



    companion object {
        fun showInfoDialogFragment(a: AppCompatActivity,
                                   avatarUrl: String,
                                   details: String) {
            showMyDialogFragment(a, newInstance(avatarUrl, details))
        }

        private fun newInstance(avatarUrl: String,
                                details: String): DetailsDialogFragment {
            val frag = DetailsDialogFragment()
            val args = Bundle()
            args.putString(ARG_AVATAR_URL, avatarUrl)
            args.putString(ARG_DETAILS, details)
            frag.arguments = args
            return frag
        }
        private const val ARG_AVATAR_URL = "ARG_AVATAR_URL"
        private const val ARG_DETAILS = "ARG_DETAILS"
    }
}
