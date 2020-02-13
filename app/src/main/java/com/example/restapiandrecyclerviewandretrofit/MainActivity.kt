package com.example.restapiandrecyclerviewandretrofit

import android.app.PendingIntent.getActivity
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)


        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://android-kotlin-fun-mars-server.appspot.com")
                .addConverterFactory(GsonConverterFactory
                    .create()).build()


        val api = retrofit.create(ApiService::class.java)


        api.fetchallusers().enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                showData(response.body()!!)
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                d("Saad", "failure")
            }
        })


    }

    private fun showData(users: List<User>) {

        recycler_view.apply {
            layoutManager = GridLayoutManager( context, 2)
            adapter = UsersAdapter(users)

        }
    }


}








