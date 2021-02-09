package com.example.navigationservicephonebook.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationservicephonebook.MainActivity
import com.example.navigationservicephonebook.R
import kotlinx.android.synthetic.main.fragment_b.*


class BFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onStart() {
        super.onStart()

        B_to_C_Button.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_BFragment_to_CFragment)
        }

        B_to_A_Button.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_BFragment_to_AFragment)
        }
    }
}