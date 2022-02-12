package com.check24.productlist.view

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.check24.base.baseEntities.BaseActivity
import com.check24.productdetails.navigation.ProductDetailsNavigation
import com.check24.productlist.R
import com.check24.productlist.databinding.ActivityProductListBinding
import com.check24.productlist.view.adpter.ProductsAdapter
import com.check24.productlist.viewModel.ProductListUiModel
import com.check24.productlist.viewModel.ProductsItemUiModel
import com.check24.productlist.viewModel.ProductsListUiState
import com.check24.productlist.viewModel.productsListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class productsListActivity : BaseActivity<ActivityProductListBinding>() {


    private val mViewModel by inject<productsListViewModel>()

    private val mAdapter = ProductsAdapter {
        showDetails(it)
    }

    private fun showDetails(it: ProductsItemUiModel) {
        ProductDetailsNavigation().startDetailsScreen(it.dto)
    }

    override fun getLayoutRes(): Int = R.layout.activity_product_list

    override fun getToolbarTitle(): Any? = R.string.app_name

    override fun onViewAttach() {
        super.onViewAttach()
        setUpRv()
        observeData()
        hiddenBackButton()
    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        mViewModel.refresh()
    }

    private fun hiddenBackButton() {
        mBinding.root.findViewById<Toolbar>(com.check24.base.R.id.toolbar).setNavigationIcon(null);

    }

    private fun observeData() {
        lifecycleScope.launch {
            mViewModel.uiState
                .flowWithLifecycle(lifecycle)
                .collect {
                    when (it) {
                        is ProductsListUiState.Loading -> showLoading(mBinding.stateful)
                        is ProductsListUiState.Error -> showError(
                            mBinding.stateful,
                            it.errorMsg
                        ) { mViewModel.fetchData() }
                        is ProductsListUiState.Success -> {
                            showContent(mBinding.stateful)
                            bindData(it.data)
                        }
                    }
                }
        }
    }

    private fun bindData(data: ProductListUiModel) {
        mAdapter.submitList(data.products)
        mBinding.swipeRefreshLayout.isRefreshing = false;
        mBinding.header.text = data.headerTitle;
        mBinding.headerDetails.text = data.headerDescription;
        mBinding.tabFilter.removeAllTabs()
        data.filters.forEach {
            mBinding.tabFilter.addTab(mBinding.tabFilter.newTab().setText(it));
        }
        mBinding.tabFilter.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mAdapter.submitList(mViewModel.filterData(tab?.text.toString()))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpRv() {
        mBinding.rv.adapter = mAdapter
        mBinding.swipeRefreshLayout.setOnRefreshListener(refreshListener);

    }
}