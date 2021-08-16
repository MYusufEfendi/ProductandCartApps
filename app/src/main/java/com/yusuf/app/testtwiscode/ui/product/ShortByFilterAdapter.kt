package com.yusuf.app.testtwiscode.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.app.testtwiscode.databinding.FilterListItemBinding
import com.yusuf.app.testtwiscode.model.ShortBytable

class ShortByFilterAdapter(private val listener: ShotByListener) :
    RecyclerView.Adapter<ShortByViewHolder>() {

    interface ShotByListener {
        fun onClickedShortBy(data: ShortBytable, qty: Int)
    }

    private val items = ArrayList<ShortBytable>()

    fun setItems(items: ArrayList<ShortBytable>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortByViewHolder {
        val binding: FilterListItemBinding =
            FilterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShortByViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShortByViewHolder, position: Int) =
        holder.bind(items[position])
}

class ShortByViewHolder(
    private val itemBinding: FilterListItemBinding,
    private val listener: ShortByFilterAdapter.ShotByListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var data: ShortBytable


    @SuppressLint("SetTextI18n")
    fun bind(item: ShortBytable) {
        this.data = item

        itemBinding.radio.isChecked = data.is_selected
        itemBinding.radio.text = data.name

        itemBinding.radio.setOnClickListener {

        }

    }


}

