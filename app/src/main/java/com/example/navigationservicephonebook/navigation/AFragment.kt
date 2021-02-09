package com.example.navigationservicephonebook.navigation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.navigationservicephonebook.MainActivity
import com.example.navigationservicephonebook.service.MyService
import com.example.navigationservicephonebook.R
import kotlinx.android.synthetic.main.fragment_a.*


class AFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onStart() {
        super.onStart()
        val activityAsMain = (activity as MainActivity)

        A_to_B_Button.setOnClickListener {
            activityAsMain.navController.navigate(R.id.action_AFragment_to_BFragment)
        }

        A_to_C_Button.setOnClickListener {
            activityAsMain.navController.navigate(R.id.action_AFragment_to_CFragment)
        }

        serviceStartButton.setOnClickListener {
            activityAsMain.startService(Intent(activityAsMain, MyService::class.java))
        }

        serviceStopButton.setOnClickListener {
            activityAsMain.stopService(Intent(activityAsMain, MyService::class.java))
        }

        contactListButton.setOnClickListener {
            ActivityCompat.requestPermissions(
                activityAsMain, arrayOf(Manifest.permission.READ_CONTACTS),
                200
            )
            if (ActivityCompat.checkSelfPermission(
                    activityAsMain,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                activityAsMain.navController.navigate(R.id.action_AFragment_to_contactListFragment)
            }
        }
    }
}
