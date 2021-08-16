package com.yusuf.app.testtwiscode.ui.product

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yusuf.app.testtwiscode.R
import com.yusuf.app.testtwiscode.databinding.BottomSeetFilterBinding
import com.yusuf.app.testtwiscode.databinding.ProductFragmentBinding
import com.yusuf.app.testtwiscode.model.CartTable
import com.yusuf.app.testtwiscode.model.CategoryTable
import com.yusuf.app.testtwiscode.model.ProductTable
import com.yusuf.app.testtwiscode.model.ShortBytable
import com.yusuf.app.testtwiscode.ui.BaseFragment
import com.yusuf.app.testtwiscode.utils.GridSpacingItemDecoration
import com.yusuf.app.testtwiscode.utils.LinearSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ProductFragment : BaseFragment(), ProductAdapter.ProductItemListener, CategoryFilterAdapter.CategoryListener,ShortByFilterAdapter.ShotByListener {

    private lateinit var binding: ProductFragmentBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var categoryAdapter: CategoryFilterAdapter
    private lateinit var shortbyAdapter: ShortByFilterAdapter
    private val itemSpace by lazy { resources.getDimensionPixelSize(R.dimen._8sdp) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.letsLoad = arguments?.getBoolean("loadFalse") == null
        setupRecyclerView()
        setupObservers()
        setupUi()
    }

    private fun setupUi(){
        binding.vCateegory.setOnClickListener {
            bottomSheetCategory()
        }
        binding.vFilter.setOnClickListener {
            bottomSheetShortby()
        }
    }


    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(this)
        binding.productRv.apply {
            addItemDecoration(
                GridSpacingItemDecoration(
                    2,
                    itemSpace,
                    true
                )
            )
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupObservers(categoryId:String = "") {
        lifecycleScope.launch {
            viewModel.loadProduct(categoryId).collectLatest {
                try {
                    val data = it
                    if (!data.isNullOrEmpty()) productAdapter.setItems(ArrayList(data))
                } catch (e: Exception) {
                    Timber.e("load home e $e")
                }
            }
        }

        viewModel.Loading.observe(viewLifecycleOwner, Observer {
            if (it) loading() else dismissloading()
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if(it!="")
                toast(it)
        })
    }


    override fun onClickedProduct(product: ProductTable, qty: Int) {
        lifecycleScope.launch {
            try {
                var qty = 0
                if (viewModel.cartRepository.getQty(product.id) != null) {
                    val getqty = viewModel.cartRepository.getQty(product.id)
                    qty = getqty+1
                }
                if(qty == 0){
                    viewModel.cartRepository.insertCart(CartTable.ProductToCart(product,qty+1))
                }else{
                    viewModel.cartRepository.updateFromHomepage(qty,product.id)
                }
                Toast.makeText(requireContext(), "dimasukkan ke keranjang", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: java.lang.Exception) {
                Timber.e("onclick error $e")
            }
        }
    }

    fun bottomSheetCategory(){
        val dialog = BottomSheetDialog(requireContext())
        val bindingDialog by lazy { BottomSeetFilterBinding.inflate(layoutInflater) }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = bindingDialog.root
        categoryAdapter = CategoryFilterAdapter(this)
        lifecycleScope.launch {
            val data = viewModel.getCategory()
            categoryAdapter.setItems(ArrayList(data))
            Timber.e("data category $data")
        }
        bindingDialog.rvCategory.apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            adapter = categoryAdapter
        }
        dialog.setContentView(view)
        dialog.show()

    }

    fun bottomSheetShortby(){
        val dialog = BottomSheetDialog(requireContext())
        val bindingDialog by lazy { BottomSeetFilterBinding.inflate(layoutInflater) }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = bindingDialog.root
        shortbyAdapter = ShortByFilterAdapter(this)
        lifecycleScope.launch {
            val data = viewModel.getShortBy()
            shortbyAdapter.setItems(ArrayList(data))
            Timber.e("data category $data")
        }
        bindingDialog.rvCategory.apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            adapter = shortbyAdapter
        }
        dialog.setContentView(view)
        dialog.show()

    }

    override fun onClickedCategory(data: CategoryTable) {}
    override fun onClickedShortBy(data: ShortBytable, qty: Int) {}
}
