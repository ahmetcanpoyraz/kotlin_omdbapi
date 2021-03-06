package com.example.kotlin_omdbapi2.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.kotlin_omdbapi2.R
import com.example.kotlin_omdbapi2.ui.auth.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backButton.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
    }


}