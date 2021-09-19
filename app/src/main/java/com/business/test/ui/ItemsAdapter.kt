package com.business.test.ui

import android.annotation.SuppressLint

import android.content.Intent

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions

import com.business.test.R
import com.business.test.databinding.LayoutRowListBinding
import com.business.test.model.NasaPost
import com.business.test.util.Constants.actualParser
import com.business.test.util.Constants.baseDateParser
import com.google.gson.Gson
import java.text.ParseException

class ItemsAdapter(private var detailsList:List<NasaPost>):RecyclerView.Adapter<ItemsAdapter.VH>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        val binding=LayoutRowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

       val staff=detailsList[position]

       if(staff!=null){

           holder.bind(staff)
       }


    }

    override fun getItemCount()=detailsList.size

    class VH(private val binding: LayoutRowListBinding):RecyclerView.ViewHolder(binding.root) {
        val context=itemView.context
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(nasaPost: NasaPost) {

            binding.apply {

                Glide.with(itemView.context)
                    .asBitmap()
                    .load(nasaPost.url)
                    .circleCrop()
                    .placeholder(R.drawable.splash_icon)
                    .into(ivPic)

                tvName.text = nasaPost.title
                tvName.isSelected = true
                try {

                    val da=baseDateParser.parse(nasaPost.date)
                    val parsedDate = actualParser.format(da!!)
                    tvDate.text = "Date: $parsedDate"

                }catch (e:ParseException){


                }

                cardClick.setOnClickListener {
                    val intent=Intent(context,ActivityDetails::class.java)
                    intent.putExtra("details",Gson().toJson(nasaPost))
                    context.startActivity(intent)
                }



            }


        }
    }


}