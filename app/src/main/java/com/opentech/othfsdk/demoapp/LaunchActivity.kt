package com.opentech.othfsdk.demoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.opentech.othfclientsdk.OthfApp
import com.opentech.othfsdk.demoapp.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLaunch.setOnClickListener {
            OthfApp.getInstance().launch(this, null, null)
        }

        OthfApp.getInstance().onFirstActivityCreated()
    }
}
