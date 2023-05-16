package com.montagat.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.montagat.databinding.ItemMontagBinding
import com.montagat.interfaces.DetailsMontagat
import com.montagat.model.MontagModel

class MontagAdapter(private val detailsMontagat: DetailsMontagat) : RecyclerView.Adapter<MontagAdapter.MontagVH>() {

    private val differCallBack = object : DiffUtil.ItemCallback<MontagModel>() {
        override fun areItemsTheSame(oldItem: MontagModel, newItem: MontagModel): Boolean {
            return oldItem.description == newItem.description &&  oldItem.image == newItem.image && oldItem.name == newItem.name && oldItem.oldPrice.toString() == newItem.oldPrice.toString() && oldItem.price.toString() == newItem.price.toString()
        }

        override fun areContentsTheSame(oldItem: MontagModel, newItem: MontagModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MontagVH {
        return MontagVH(ItemMontagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MontagVH, position: Int) {
        differ.currentList[position].apply {
//            Glide
//                .with(holder.itemView.context)
//                .load(this.image)
//                .into(holder.bind.imgItemMontag)
            holder.bind.imgItemMontag.load(this.image) {
                this.crossfade(3000)
//                this.crossfade(true)
//                this.transformations(RoundedCornersTransformation(10f))
            }

            holder.bind.txtNameItemMontag.text = this.name
            holder.bind.txtPriceItemMontag.text = "${this.price} EGP"
            holder.bind.txtOldPriceItemMontag.also {
                it.text = "${this.oldPrice} EGP"
                it.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            holder.itemView.setOnClickListener {
                detailsMontagat.clickedOnMontagatForDetails(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MontagVH(binding: ItemMontagBinding) : RecyclerView.ViewHolder(binding.root) {
        val bind = binding
    }
}