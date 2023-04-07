package com.example.catapiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catapiproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //set button handler.
        binding.btnGetCatData.setOnClickListener { printCatData() }

    }
    fun printCatData(){

    }//end printCatData
}