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
import com.relsellglobal.kotlinnavigationexample.databinding.FragmentSpecifyAmountBinding
import java.math.BigDecimal

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpecifyAmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpecifyAmountFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recipient : String

    var specifyAmountFragmentBinding : FragmentSpecifyAmountBinding ?= null
    var navController : NavController ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments!!.getString("recipient").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        specifyAmountFragmentBinding = FragmentSpecifyAmountBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return specifyAmountFragmentBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        specifyAmountFragmentBinding?.sendBtn?.setOnClickListener(this)
        val message = "Sending money to $recipient"
        specifyAmountFragmentBinding?.recipient?.text = message
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cancel_btn -> activity?.onBackPressed()
            R.id.send_btn -> {
                if (!TextUtils.isEmpty(specifyAmountFragmentBinding?.inputAmount?.text.toString())) {
                    var amount = Money(BigDecimal(specifyAmountFragmentBinding?.inputAmount?.text.toString()))
                    var bundle = bundleOf("recipient" to recipient, "amount" to amount )
                    navController?.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment,bundle)
                } else {
                    Toast.makeText(activity,"Enter an amount",Toast.LENGTH_SHORT).show()
                }
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
         * @return A new instance of fragment SpecifyAmountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpecifyAmountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}