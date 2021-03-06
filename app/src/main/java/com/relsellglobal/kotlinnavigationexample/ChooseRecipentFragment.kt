package com.relsellglobal.kotlinnavigationexample

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.relsellglobal.kotlinnavigationexample.databinding.FragmentChooseRecipentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseRecipentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseRecipentFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var fragmentChooseRecipentFragment : FragmentChooseRecipentBinding ?= null

    var navController : NavController ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentChooseRecipentFragment = FragmentChooseRecipentBinding.inflate(inflater,container,false)
        return fragmentChooseRecipentFragment?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        fragmentChooseRecipentFragment?.cancelBtn?.setOnClickListener(this)
        fragmentChooseRecipentFragment?.nextBtn?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.next_btn -> {
                if(!TextUtils.isEmpty(fragmentChooseRecipentFragment?.inputRecipient?.text.toString())) {
                    val bundle = bundleOf("recipient" to fragmentChooseRecipentFragment?.inputRecipient?.text.toString())
                    navController?.navigate(R.id.action_chooseRecipentFragment_to_specifyAmountFragment2,bundle)
                } else {
                    Toast.makeText(activity,"Enter an recipient", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.cancel_btn -> {
                activity?.onBackPressed()
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChooseRecipentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChooseRecipentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}