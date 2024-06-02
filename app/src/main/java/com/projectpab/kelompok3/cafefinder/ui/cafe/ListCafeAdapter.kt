package com.projectpab.kelompok3.cafefinder.ui.cafe

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projectpab.kelompok3.cafefinder.R
import com.projectpab.kelompok3.cafefinder.Cafe
import com.projectpab.kelompok3.cafefinder.CafeDetailActivity

class ListCafeAdapter (private val listCafe: ArrayList<Cafe>) : RecyclerView.Adapter<ListCafeAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_song_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_song_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_cafe, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listCafe.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, desc, img, audio) = listCafe[position]
        holder.imgPhoto.setImageResource(img)
        holder.tvName.text = name
        holder.tvDescription.text = desc
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CafeDetailActivity::class.java).apply {
                putExtra("SONG_NAME", name)
                putExtra("SONG_DESC", desc)
                putExtra("SONG_IMG_RES_ID", img)
                putExtra("SONG_AUDIO_RES_ID", audio)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Cafe)
    }
}