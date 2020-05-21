package com.deved.coroutinesmvp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.GrayscaleTransformation
import com.deved.coroutinesmvp.R
import com.deved.coroutinesmvp.common.basicDiffUtil
import com.deved.coroutinesmvp.data.models.detail.ContentResponsePlace
import kotlinx.android.synthetic.main.item_places.view.*

class DetailAdapter : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    var placeList: List<ContentResponsePlace> by basicDiffUtil(
        emptyList(), { old, new -> old.nameTourist == new.nameTourist }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_places, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = placeList.size

    override fun onBindViewHolder(holder: DetailAdapter.ViewHolder, position: Int) {
        val place = placeList[position]
        holder.bind(place)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal fun bind(place: ContentResponsePlace) {
            itemView.imageView_background.load(place.image) {
                crossfade(true)
                crossfade(500)
            }
            itemView.textView_nameCity.text = place.nameTourist
        }
    }
}