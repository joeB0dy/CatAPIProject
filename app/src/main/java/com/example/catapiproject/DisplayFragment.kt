package com.example.catapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlin.random.Random

import com.example.catapi.databinding.DisplayFragmentBinding

class DisplayFragment() : Fragment() {

    private lateinit var binding : DisplayFragmentBinding

    private lateinit var textInput: EditText
    private lateinit var expression : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DisplayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    fun updateDisplay(value : String) {
        //if value is =, then clear input, and put result in display
        Log.i("DisplayFragment", value)
        //var catValue = Random.nextInt(1, 5)
        //var imageName = "@drawable/cat" + catValue
        //var resourceID = resources.getIdentifier(imageName, "drawable", getPackageName())


        binding.CatName.setText(binding.CatName.text.toString() + value)
        //binding.CatImage.setImageResource(resourceID)
        binding.CatTemperament.setText(binding.CatTemperament.text.toString() + value)
        binding.CatOrigin.setText(binding.CatOrigin.text.toString() + value)

    }
}
