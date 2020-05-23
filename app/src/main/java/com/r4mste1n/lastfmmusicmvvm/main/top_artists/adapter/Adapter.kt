package com.r4mste1n.lastfmmusicmvvm.main.top_artists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.root.extensions.ImageTransformType
import com.r4mste1n.lastfmmusicmvvm.root.extensions.formatCount
import com.r4mste1n.lastfmmusicmvvm.root.extensions.loadImage
import kotlinx.android.synthetic.main.top_artist_item.view.*

/**
 * Created by Alex Shtain on 19.04.2020.
 */
class Adapter(private val clickListener: (item: AdapterData) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val data = ArrayList<AdapterData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_artist_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(data[position])
            itemView.setOnClickListener { clickListener(data[position]) }
        }
    }

    override fun getItemCount() = data.size

    fun setData(newData: List<AdapterData>) {
        data.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: AdapterData) {
            itemView.apply {
                name.text = data.name
                hearersCount.text = context.resources.getString(
                    R.string.listeners,
                    data.hearersCount.formatCount()
                )
                photo.loadImage(
                    url = data.photoUrl ?: "",
                    transformType = ImageTransformType.CenterCrop
                )
            }
        }
    }
}