package com.example.restapiandrecyclerviewandretrofit


import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.util.Log.d
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions


class UsersAdapter(private val users: List<User>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    private lateinit var context: Context //creating context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        context = parent.context //setting context used for glide and other stuff intents, whereever you want to use "this" you have to substitute context

        val view = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false)
        return ViewHolder(view)


    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {


        val user = users[position]


        val String = "${user.price}"
        val priceInDou: Double? = String.toDouble() / 1000000

        holder.theid.text = "${user.id}, Mars, Milky Way"

        //For displaying the image
        val imgUri = user.img_src.toUri().buildUpon().scheme("https").build() //solves the http to https error
        Glide.with(context).load(imgUri).apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(holder.forimage)


        if (user.type == "rent"){holder.rentsignImageView.visibility = View.VISIBLE}
        else{holder.rentsignImageView.visibility = View.GONE}


        //On Click Listener
        holder.forimage.setOnClickListener() {

            val intent = Intent(context, recycler_view_detail::class.java)
            intent.putExtra("id", user.id)
            intent.putExtra("price", priceInDou.toString())
            intent.putExtra("imageURI",imgUri.toString())
            intent.putExtra("type", user.type)
            context.startActivity(intent)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val theid: TextView = itemView.findViewById(R.id.id)
        val forimage: ImageView = itemView.findViewById(R.id.imageView)
        val rentsignImageView: ImageView = itemView.findViewById(R.id.renticonimageview) }

}
