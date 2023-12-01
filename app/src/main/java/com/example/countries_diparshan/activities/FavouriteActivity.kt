package com.example.countries_diparshan.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.countries_diparshan.R
import com.example.countries_diparshan.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}