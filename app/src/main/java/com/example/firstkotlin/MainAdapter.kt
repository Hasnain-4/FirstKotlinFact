package com.example.final_factapp
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstkotlin.Posts
import com.example.firstkotlin.R
import com.example.firstkotlin.ShowContent
import com.squareup.picasso.Picasso
import java.util.ArrayList
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.security.AccessController.getContext
class MainAdapter(context: Context,items:List<Posts>):RecyclerView.Adapter<MainAdapter.PostViewHolder>() {
    private val context:Context
    private val items:List<Posts>
    internal var url1 = "https://meta.etherealwork.net/top-ten-website/"

    init{
        this.context = context
        this.items = items
    }
    @NonNull
    override fun onCreateViewHolder(@NonNull parent:ViewGroup, viewType:Int):PostViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.video, parent, false)
        val viewHolder = PostViewHolder(view)
        viewHolder.linearLayout.setOnClickListener {
            val i = Intent(context, ShowContent::class.java)
            i.putExtra("title", items.get(viewHolder.adapterPosition).title)
            i.putExtra("name", items.get(viewHolder.adapterPosition).name)
            i.putExtra("summary", items.get(viewHolder.adapterPosition).summary)
            i.putExtra("created", items.get(viewHolder.adapterPosition).created_at)
            i.putExtra("content", items.get(viewHolder.adapterPosition).content)
            i.putExtra("image", items.get(viewHolder.adapterPosition).image_default)
            i.putExtra("video", items.get(viewHolder.adapterPosition).video_embed_code)
            context.startActivity(i)
        }
        return viewHolder
    }
    override fun onBindViewHolder(@NonNull holder:PostViewHolder, position:Int) {
        val item = items.get(position)
        holder.postTitle.text = item.title
        holder.postName.text = item.name
        holder.postCreated.text = item.created_at


        Picasso.get().load(url1 + item.image_default).into(holder.postImage)
    }
    inner class PostViewHolder(@NonNull itemView:View):RecyclerView.ViewHolder(itemView) {
        internal var postImage:ImageView = itemView.findViewById(R.id.image)
        internal var postTitle:TextView = itemView.findViewById(R.id.title)
        internal var postName:TextView = itemView.findViewById(R.id.name)
        internal var postCreated:TextView = itemView.findViewById(R.id.created_at)
        internal var linearLayout:LinearLayout = itemView.findViewById(R.id.linearlay)


    }

    override fun getItemCount(): Int {
        return items.size
    }
}