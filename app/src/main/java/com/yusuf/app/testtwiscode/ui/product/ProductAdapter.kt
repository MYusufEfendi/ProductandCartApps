package com.yusuf.app.testtwiscode.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.yusuf.app.testtwiscode.databinding.ItemProductBinding
import com.yusuf.app.testtwiscode.model.ProductTable
import com.yusuf.app.testtwiscode.utils.StringUtil

class ProductAdapter(private val listener: ProductItemListener) : RecyclerView.Adapter<ProductViewHolder>() {

    interface ProductItemListener {
        fun onClickedProduct(product: ProductTable,qty:Int)
    }

    private val items = ArrayList<ProductTable>()

    fun setItems(items: ArrayList<ProductTable>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemProductBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(items[position])
}

class ProductViewHolder(private val itemBinding: ItemProductBinding, private val listener: ProductAdapter.ProductItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var Product: ProductTable

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductTable) {
        this.Product = item
        itemBinding.data = item
        itemBinding.stringUtil = StringUtil()

        itemBinding.img.clipToOutline = true

    }

    override fun onClick(v: View?) {
        listener.onClickedProduct(Product,1)
    }
}

