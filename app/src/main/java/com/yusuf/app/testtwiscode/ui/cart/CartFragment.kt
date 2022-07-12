package com.yusuf.app.testtwiscode.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yusuf.app.testtwiscode.R
import com.yusuf.app.testtwiscode.databinding.CartFragmentBinding
import com.yusuf.app.testtwiscode.model.TransactionWithProduct
import com.yusuf.app.testtwiscode.ui.BaseFragment
import com.yusuf.app.testtwiscode.utils.LinearSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CartFragment : BaseFragment(), CartAdapter.btnListener {

    private lateinit var binding: CartFragmentBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(viewModel, this)
        binding.cartRv.apply {
            addItemDecoration(
                LinearSpaceDecoration(
                    space = resources.getDimensionPixelSize(R.dimen._8sdp),
                    customEdge = resources.getDimensionPixelSize(R.dimen._16sdp),
                    includeTop = true,
                    includeBottom = true
                )
            )
            adapter = cartAdapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            try {
                var list = viewModel.getListCart()
                if (!list.isNullOrEmpty()) cartAdapter.setItems(ArrayList(list))
                Timber.e("data cart $list")
            } catch (e: Exception) {
                Timber.e("setupObservers e $e")
            }


            //count price total

            try {
                viewModel.countPriceTotal().collectLatest {
                    binding.priceTotal = "Php. " + viewModel.stringUtil.formatCurrency(it)
                }
            } catch (e: Exception) {
                Timber.e("count price error $e")
            }


        }
    }

    override fun onPlus(item: TransactionWithProduct, position: Int) {
        lifecycleScope.launch {
            viewModel.update(
                item.cart.qty + 1,
                item.cart.id,
                item.product.price * (item.cart.qty + 1)
            )
            val list = viewModel.getListCart()
            cartAdapter.updateItem(
                list[position],
                position,
            )
            binding.executePendingBindings()
        }
    }

    override fun onMin(item: TransactionWithProduct, position: Int) {
        lifecycleScope.launch {
            if (item.cart.qty > 1) {
                viewModel.update(
                    item.cart.qty - 1,
                    item.cart.id,
                    item.product.price * (item.cart.qty + 1)
                )
                val list = viewModel.getListCart()
                cartAdapter.updateItem(
                    list[position],
                    position,
                )
            } else {
                alert(
                    "Delete Product",
                    "are you sure you want to remove this product from cart ?",
                    "Delete",
                    {
                        lifecycleScope.launch {
                            viewModel.delete(
                                item.cart.id
                            )
                            val list = viewModel.getListCart()
                            cartAdapter.setItems(list)
                        }

                    },
                    "Dismis"
                )
            }
            binding.executePendingBindings()
        }
    }
}