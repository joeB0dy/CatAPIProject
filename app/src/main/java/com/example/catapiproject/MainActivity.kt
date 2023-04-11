package com.example.catapiproject

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.catapiproject.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : FragmentActivity(), ImageFragment.ImageListener {
    private lateinit var binding : ActivityMainBinding
    //what im straight passing as "global" from printCatData to overrideCallback stuff.
    var n:String = ""
    var t:String = ""
    var o:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //set button handler.
        binding.btnGetCatData.setOnClickListener {

            printCatData()

        }
    }

    fun printCatData(){
        //initialize stuff.


        var catURL =    "https://api.thecatapi.com/v1/breeds" + "?api-key=live_jbbUIHNI836wIyACai0hKSLUeHcfqXv3DAV68q0c5xRGX7mvFJSP52DVwut4XgvY"
        var imageURL = 0
        val queue = Volley.newRequestQueue(this)    //imported Volley.

        //request a string response from provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, catURL,
            { response ->
                var catsArray : JSONArray = JSONArray(response)
//indices from 0 through catsArray.length()-1
                for(i in 0 until catsArray.length()) {
//${} is to interpolate the string /
                // uses a string template
                    var theCat : JSONObject = catsArray.getJSONObject(i)
                    //what i added.
                    var gunga :JSONObject = catsArray.getJSONObject(0)
    //now get the properties we want: name and description
                    Log.i("MainActivity",
                        "Cat name: ${theCat.getString("name")}")
                    Log.i("MainActivity",
                        "Cat description: ${theCat.getString("description")}")
                    //above works.
                    //straight up doing this. idc if bad coding naming regiment make sure it works.
                    n = "name: " + gunga.getString("name")
                    t = "temperament: " + gunga.getString("temperament")
                    o = "origin: " + gunga.getString("origin")
                    imageURL = gunga.getString("id").toInt()
                }//end for
            },
           Response.ErrorListener {
                Log.i("MainActivity", "THAT DIDN'T WORK... ")
            })  //end of stringrequest.

        onButtonClick(imageURL, n,t,o)  //passing somehow. if it works great lets just get there.
        //add to RequestQueue
        queue.add(stringRequest)


    }//end printCatData

    //what will be passed back on Callback to Fragment.

    override fun onButtonClick(imgID: Int, name: String, temp: String, origin: String) {
        val imageFragment = supportFragmentManager.findFragmentById((R.id.CatImageView))  as ImageFragment
        val textFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as ImageFragment


        imageFragment.setImageView(imgID)
        textFragment.setText(name, temp, origin) // change above.
    }
}