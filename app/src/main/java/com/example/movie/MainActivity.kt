package com.example.movie

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.movie.common.base.activity.BaseActivity
import com.example.movie.common.extensions.applySystemBarInsets
import com.example.movie.databinding.ActivityDetailBinding
import com.example.movie.databinding.ActivityMainBinding

class MainActivity :  BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun create() {
        //enableEdgeToEdge()


        binding.root.applySystemBarInsets()
        Thread.sleep(3000)
       // installSplashScreen()




    }


}
