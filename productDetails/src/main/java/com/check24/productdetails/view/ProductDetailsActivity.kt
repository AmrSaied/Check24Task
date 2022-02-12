package com.check24.productdetails.view

import android.app.Activity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ActivityUtils
import com.check24.base.baseEntities.BaseActivity
import com.check24.base.baseEntities.BaseNavigationManager.Companion.KEY_OBJECT
import com.check24.base.utils.bindImage
import com.check24.data.network.entities.ProductsItem
import com.check24.productdetails.R
import com.check24.productdetails.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProductDetailsActivity : BaseActivity<ActivityProductDetailsBinding>() {


    private val data by lazy { intent.getParcelableExtra<ProductsItem>(KEY_OBJECT)!! }

    private val mViewModel by inject<ProductDetailsViewModel>{
        parametersOf(data)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_product_details
    }

    override fun getToolbarTitle() = data.name


    override fun onViewAttach() {
        super.onViewAttach()
        setOnClickListener()
        observeData()
        openWebView()
    }

    private fun setOnClickListener() {
        mBinding.btnFav.setOnClickListener {
            mViewModel.toggleFavStatus()
        }
    }

    fun openWebView(){
        mBinding.termsTv.setOnClickListener {
            ActivityUtils.startActivity(WebViewActivity::class.java)
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            mViewModel.uiState
                .flowWithLifecycle(lifecycle)
                .collect {
                    when(it){
                        is ProductDetailsUiState.Loading -> showLoading(mBinding.stateful)
                        is ProductDetailsUiState.Error -> showError(mBinding.stateful,it.errorMsg) {  }
                        is ProductDetailsUiState.Success -> {
                            showContent(mBinding.stateful)
                            bindData(it.data)
                        }
                    }
                }
        }
    }

    private fun bindData(data: ProductDetailsUiModel) {
        bindImage(mBinding.ivImage,data.imageURL)
        mBinding.tvName.text = data.name
        mBinding.tvName.setTextColor(data.getNameColor(this))
        mBinding.tvPrice.text = data.priceTxt
        mBinding.tvLongDesc.text = data.longDescription
        mBinding.tvShortDescription.text = data.description
        mBinding.ratingBar.rating = data.rating
        mBinding.btnFav.text = data.getFavBtnText(this)
        mBinding.tvDate.text = data.releaseDate
    }
}