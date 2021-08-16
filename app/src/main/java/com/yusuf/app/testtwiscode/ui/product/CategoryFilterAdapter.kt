package com.yusuf.app.testtwiscode.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yusuf.app.testtwiscode.databinding.FilterListItemBinding
import com.yusuf.app.testtwiscode.model.CategoryTable
import timber.log.Timber

class CategoryFilterAdapter(private val listener: CategoryListener,private val bottomSheetDialog: BottomSheetDialog) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    interface CategoryListener {
        fun onClickedCategory(data: CategoryTable)
    }

    private val items = ArrayList<CategoryTable>()

    fun setItems(items: ArrayList<CategoryTable>) {
        this.items.clear()
        this.items.addAll(items)
        Timber.e("items $items")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: FilterListItemBinding =
            FilterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding, listener,bottomSheetDialog)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(items[position])
}

class CategoryViewHolder(
    private val itemBinding: FilterListItemBinding,
    private val listener: CategoryFilterAdapter.CategoryListener,
    private val bottomSheetDialog: BottomSheetDialog
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var data: CategoryTable


    @SuppressLint("SetTextI18n")
    fun bind(item: CategoryTable) {
        this.data = item
        itemBinding.radio.isChecked = data.is_selected
        itemBinding.radio.text = data.cat_name

        itemBinding.radio.setOnClickListener {
            bottomSheetDialog.dismiss()
            listener.onClickedCategory(
                data
            )
        }

    }


}

