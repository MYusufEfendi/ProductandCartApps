package com.yusuf.app.testtwiscode.ui.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.app.testtwiscode.databinding.CartItemBinding
import com.yusuf.app.testtwiscode.model.TransactionWithProduct
import com.yusuf.app.testtwiscode.utils.StringUtil

class CartAdapter(private val viewmodel: CartViewModel, private val listener: btnListener) :
    RecyclerView.Adapter<ProductViewHolder>() {

    interface btnListener {
        fun onPlus(item: TransactionWithProduct, position: Int)
        fun onMin(item: TransactionWithProduct, position: Int)
    }

    private val items = ArrayList<TransactionWithProduct>()

    fun setItems(items: List<TransactionWithProduct> = emptyList()) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    fun updateItem(item:TransactionWithProduct, position: Int){
        this.items.set(
            position,
            item
        )
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: CartItemBinding =
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, viewmodel, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(items[position], position)
}

class ProductViewHolder(
    private val itemBinding: CartItemBinding,
    private val viewModel: CartViewModel,
    private val listener: CartAdapter.btnListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var data: TransactionWithProduct

    @SuppressLint("SetTextI18n")
    fun bind(item: TransactionWithProduct, position: Int) {
        this.data = item
        itemBinding.item = item
        itemBinding.stringUtil = viewModel.stringUtil
        itemBinding.weightKg = (item.product.weight * item.cart.qty).toString()
        itemBinding.img.clipToOutline = true

        itemBinding.btnPlus.setOnClickListener {
            listener.onPlus(item, position)
        }
        itemBinding.btnMinus.setOnClickListener {
            listener.onMin(item, position)
        }

    }

}