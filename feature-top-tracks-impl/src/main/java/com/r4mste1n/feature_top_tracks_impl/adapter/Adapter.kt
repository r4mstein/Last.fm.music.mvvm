package com.r4mste1n.feature_top_tracks_impl.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r4mste1n.core_common.extensions.ImageTransformType
import com.r4mste1n.core_common.extensions.loadImage
import com.r4mste1n.feature_top_tracks_impl.R
import kotlinx.android.synthetic.main.top_track_item.view.*

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val data = ArrayList<AdapterData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_track_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(data[position])
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
                name.text = data.name.orEmpty()
                singer.text = data.singer.orEmpty()
                photo.loadImage(
                    url = data.photoUrl.orEmpty(),
                    transformType = ImageTransformType.CenterCrop
                )
            }
        }
    }
}