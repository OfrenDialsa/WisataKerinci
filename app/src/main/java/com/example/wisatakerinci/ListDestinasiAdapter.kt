package com.example.wisatakerinci

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wisatakerinci.DetailActivity.Companion.DESTINATION_KEY

class ListDestinasiAdapter(private val listDestinasi: ArrayList<Destinasi>):
    RecyclerView.Adapter<ListDestinasiAdapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_destinasi, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listDestinasi.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (judul, desc, photo) = listDestinasi[position]

        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = judul
        holder.tvDescription.text = desc
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DESTINATION_KEY, listDestinasi[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Destinasi)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description_short)
    }

}