package com.example.ililo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ililo.Home.HomeFragment
import com.example.ililo.My.view.MyPageFragment
import com.example.ililo.Park.ParkFragment
import com.example.ililo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        binding.navBottom.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_park -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, ParkFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
        }
    }
}