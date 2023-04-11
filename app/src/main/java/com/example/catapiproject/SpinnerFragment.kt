package com.example.catapi

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.catapi.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import com.example.catapi.databinding.SpinnerFragmentBinding

class SpinnerFragment : Fragment() {


    private lateinit var binding: SpinnerFragmentBinding
    var activityCallback : SpinnerFragment.SpinnerListener ?= null

    interface SpinnerListener {
        fun onSpinnerClick(text : String)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SpinnerFragmentBinding.inflate(inflater, container, false)
        return binding.root

        /*
        var spinnerData = ArrayList<String>()
        spinnerData.add("Cat1")
        spinnerData.add("Cat2")
        spinnerData.add("Cat3")
        spinnerData.add("Cat4")
        spinnerData.add("Cat5")

        var arrayAdapter = ArrayAdapter<String>(this,
            R.layout.simple_spinner_dropdown_item,
            spinnerData)

        binding.theSpinner.adapter = arrayAdapter

        binding.theSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.theTextView.text = binding.theSpinner.selectedItem.toString()
            }

        }

        binding.theSpinner.setOnClickListener { printCatData() }

        return binding.root
        */

    }

    // method to interact with API
    fun printCatData() {
        var catUrl = "https://api.thecatapi.com/v1/breeds" +
                "?api_key=live_TtTUXgkBvL1j5aJP5B9dVD44hilKHHvJhetLptxeifoUveVzXcDQi6fOY5DpavSA"

        val queue = Volley.newRequestQueue(getActivity(), )

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            Response.Listener<String> { response ->
                var catsArray : JSONArray = JSONArray(response)

                //indices from 0 through catsArray.length()-1
                for(i in 0 until catsArray.length()) {
                    //${} is to interpolate the string /
                    // uses a string template
                    var theCat : JSONObject = catsArray.getJSONObject(i)

                    //now get the properties we want:  name, temperament, and origin
                    Log.i("MainActivity", "Cat name: ${theCat.getString("name")}")
                    activityCallback?.onSpinnerClick("${theCat.getString("name")}")
                    Log.i("MainActivity", "Cat Image: ${theCat.getString("url")}")
                    activityCallback?.onSpinnerClick("${theCat.getString("url")}")
                    Log.i("MainActivity", "Cat temperament: ${theCat.getString("temperament")}")
                    activityCallback?.onSpinnerClick("${theCat.getString("temperament")}")
                    Log.i("MainActivity", "Cat origin: ${theCat.getString("origin")}")
                    activityCallback?.onSpinnerClick("${theCat.getString("origin")}")
                }//end for
            },
            Response.ErrorListener {
                Log.i("MainActivity", "That didn't work!")
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }//end printCatData

    override fun onAttach(context : Context) {
        super.onAttach(context)

        try {
            activityCallback = context as MainActivity
            Log.i("SpinnerFragment", activityCallback.toString())
            Log.i("SpinnerFragment", "In the try after setting callback")
        }
        catch (e : ClassCastException) {
            throw ClassCastException(context.toString() + " must implement SpinnerListener")
        }
    }


}
