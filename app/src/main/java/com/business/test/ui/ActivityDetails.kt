package com.business.test.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.module.AppGlideModule
import com.business.test.R
import com.business.test.databinding.ActivityDetailsBinding
import com.business.test.model.NasaPost
import com.google.gson.Gson


class ActivityDetails : AppCompatActivity() {
    lateinit var  binding:ActivityDetailsBinding
    lateinit var details: NasaPost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        var tvHeader=binding.appBar.findViewById<AppCompatTextView>(R.id.tv_header)

        tvHeader.text=getString(R.string.detail_header_name)

        var ivBack=binding.appBar.findViewById<ImageView>(R.id.iv_nav_back)

        ivBack.visibility=View.VISIBLE

        ivBack.setOnClickListener {

           onBackPressed()
        }
       /* if(intent!=null){

          binding.apply {
              details = Gson().fromJson(intent.getStringExtra("details"), NasaPost::class.java)


              Glide.with(binding.root).asBitmap()
                  .placeholder(R.drawable.splash_icon)
                  .thumbnail(0.1f)
                  .transition(withCrossFade())
                  .load(details.hdurl).into(binding.iv)

              tvDsc.text = details.explanation
              tvTitle.text = details.title
              tvDate.text = details.date
              tvTitle.isSelected = true

              tvDsc.movementMethod=ScrollingMovementMethod()
          }




        }*/
    }



    override fun onStart() {
        super.onStart()

        if(intent!=null){

            binding.apply {
                details = Gson().fromJson(intent.getStringExtra("details"), NasaPost::class.java)


                Glide.with(binding.root).asBitmap()
                    .placeholder(R.drawable.splash_icon)
                    .thumbnail(0.1f)
                    .transition(withCrossFade())
                    .load(details.hdurl).into(binding.iv)

                tvDsc.text = details.explanation
                tvTitle.text = details.title
                tvDate.text = details.date
                tvTitle.isSelected = true

                tvDsc.movementMethod=ScrollingMovementMethod()
            }




        }
    }

}