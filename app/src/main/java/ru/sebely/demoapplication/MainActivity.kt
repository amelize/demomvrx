package ru.sebely.demoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.airbnb.mvrx.launcher.MavericksLauncherActivity
import ru.sebely.demoapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //
        // You can run mocks now and here
        //
        // MavericksLauncherActivity.show(this)
    }
}