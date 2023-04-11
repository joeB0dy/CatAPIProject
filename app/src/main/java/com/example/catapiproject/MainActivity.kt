package com.example.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter

import com.example.catapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SpinnerFragment.SpinnerListener, OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var spinnerData = ArrayList<String>()
        spinnerData.add("Cat1")
        spinnerData.add("Cat2")
        spinnerData.add("Cat3")
        spinnerData.add("Cat4")
        spinnerData.add("Cat5")

        binding = ActivityMainBinding.inflate(layoutInflater)

        var arrayAdapter = ArrayAdapter<String>(this,
            R.layout.activity_main, R.id.theTextView,
            spinnerData)

        binding.theSpinner.adapter = arrayAdapter

        setContentView(binding.root)

        binding.theSpinner.onItemSelectedListener = this

        supportActionBar?.hide()

        /*
        binding = ActivityMainBinding.inflate(layoutInflater)


         */
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        binding.theTextView.text = binding.theSpinner.selectedItem.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onSpinnerClick(value : String) {
        val displayFragment = supportFragmentManager.findFragmentById(R.id.outputFragmentView) as DisplayFragment
        displayFragment.updateDisplay(value)
    }

}
