package com.example.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Verification.newInstance] factory method to
 * create an instance of this fragment.
 */
class Verification : Fragment() {

    private val args: VerificationArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVerify= view.findViewById<Button>(R.id.clickBtn)


       btnVerify.setOnClickListener {
           when (args.Type) {
               "signUp" -> {
                   findNavController().navigate(R.id.goingToDashBoardFragment)
               }
               "forgot_password" -> {
                   findNavController().navigate(R.id.OTPToReset)
               }
               else -> {
                   findNavController().navigate(R.id.goingToDashBoardFragment)
               }
        }
       }


    }
}