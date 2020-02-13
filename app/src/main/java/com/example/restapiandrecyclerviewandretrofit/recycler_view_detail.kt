package com.example.restapiandrecyclerviewandretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_row.*

class recycler_view_detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_detail)


        IncomingIntents()




    }





    private fun IncomingIntents() {

        if (intent.hasExtra("id") && intent.hasExtra("price") && intent.hasExtra("type") && intent.hasExtra("imageURI")){

            val tnsid = intent.getStringExtra("id")
            val tnsPrice = intent.getStringExtra("price")




            loadImage() //for loading image in the testView

            settype() // for setting the type

            val tnsidTextView: TextView = findViewById(R.id.newid)
            val tnspriceTextView: TextView = findViewById(R.id.newprice)

            tnsidTextView.setText("${tnsid} Mars, Milky Way")
            tnspriceTextView.setText("Price: ${tnsPrice} Million")

        }

    }


    private fun loadImage(){

        val tnsimage = intent.getStringExtra("imageURI")
        val tnsimageImageView: ImageView = findViewById(R.id.newPropertyimageView)

        Glide.with(this).load(tnsimage).apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(tnsimageImageView)


    }


    private fun settype(){

        val tnstypeTextView: TextView = findViewById(R.id.newtype)
        val tnstype = intent.getStringExtra("type")

        if (tnstype == "buy") {tnstypeTextView.setText("Available for Purchase")}
        else {tnstypeTextView.setText("Available to Rent")}

    }


}
