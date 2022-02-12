package com.check24.productdetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.check24.base.baseEntities.BaseActivity
import com.check24.productdetails.R
import com.check24.productdetails.databinding.ActivityProductDetailsBinding
import com.check24.productdetails.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override fun getToolbarTitle() = R.string.terms
    override fun getLayoutRes(): Int = R.layout.activity_web_view


    override fun onViewAttach() {
        super.onViewAttach()
        mBinding.webView.loadUrl("https://m.check24.de/rechtliche-hinweise/?deviceoutput=app")
    }

}