package com.tinktest.sotiris.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tinktest.sotiris.R
import com.tinktest.sotiris.databinding.ItemDoggoBinding
import com.tinktest.sotiris.models.PugInfo

class PugsAdapter : RecyclerView.Adapter<PugsAdapter.PostViewHolder>() {
    private var items: List<PugInfo>? = null
    private lateinit var binding: ItemDoggoBinding
    lateinit var itemClickListener: ItemClickListener

    init {
        items = emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = ItemDoggoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val pugWithInfo = items?.get(position)

        holder.bind(pugWithInfo!!)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    fun setItems(data: List<PugInfo>) {
        items = data
        notifyDataSetChanged()
    }

    inner class PostViewHolder(private val itemBinding: ItemDoggoBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(postWithImage: PugInfo) {
            itemBinding.title.text = postWithImage.name
            itemBinding.shortDescription.text = postWithImage.description
            itemBinding.photo.load(postWithImage.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.baseline_pets_24)
                error(R.drawable.ic_error_outline_24px)
            }

            itemBinding.photo.transitionName = postWithImage.name

            itemBinding.root.setOnClickListener {
                itemClickListener.onClick(it, itemBinding.photo, items!![adapterPosition])
            }
        }
    }

    interface ItemClickListener {
        fun onClick(aView: View, photo: ImageView, item: PugInfo)
    }
}
