package com.example.catapiproject

import android.content.Context
import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import com.android.volley.toolbox.ImageLoader.ImageListener
import com.example.catapiproject.databinding.FragmentImageBinding

class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding

    //initialize material for image stuff to be passed by API in Activity. ActivityCallBack.
    var activityCallBack: ImageFragment.ImageListener? = null

    interface ImageListener{
        fun onButtonClick(imgID: Int, name: String, temp: String, origin: String) //i guess when the button clicks it will pass an image
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        try{
            activityCallBack = context as ImageListener
        }
        catch(e: ClassCastException){
            throw ClassCastException(context.toString() + "MUST IMPLEMENT IMAGELISTENER")
        }
    }

    private fun buttonClicked(view: View){
        activityCallBack?.onButtonClick(binding.CatImageView.id, binding.tvName.text.toString(), binding.tvTemperment.toString(), binding.tvOrigin.toString() )
    }
    //end of Activity CallBack Stuff.

    companion object {
        fun newInstance() = ImageFragment()
    }

    private lateinit var viewModel: ImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflate layout for this fragment.
        binding = FragmentImageBinding.inflate(inflater, container, false)

        //where the buttons will be coded for the fragment itself.
            //image
            //text descriptions within Scrollview.

        //How to add multiple


        return binding.root
    }
    fun setImageView( img: Int){
       //material for function to be passed indirectly in Activity.
        var ImgURL = "https://api.thecatapi.com/v1/images"

        binding.CatImageView.load(ImgURL + img.toString())

    }

    fun setText(name: String, temp: String, origin: String){
        //material indirectly passed in MainActivity.
        binding.tvName.text = name
        binding.tvTemperment.text = temp
        binding.tvOrigin.text = origin
    }//end of function.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}