package com.example.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val tvSignUp = view.findViewById<TextView>(R.id.SignUp)
        val btnResetPw = view.findViewById<TextView>(R.id.btnFogortPw)
        val emailText = view.findViewById<EditText>(R.id.EmailText)

        btnLogin.setOnClickListener {
            val email = emailText.text.toString().trim()

            if (email.isNotEmpty()){
                val action= LoginFragmentDirections.loginTodashBoardFragment(email)
                findNavController().navigate(action)
            }else{
                emailText.error="pls enter ur email"
            }

        }

        tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.goingtosignup)
        }

        btnResetPw.setOnClickListener {
            btnResetPw.setOnClickListener {
                val action = LoginFragmentDirections.loginToVerification("forgot_password")
                findNavController().navigate(action)
            }

        }
    }


}