package com.check24.task.ui.splash

import android.os.Handler
import android.os.Looper
import com.check24.base.baseEntities.BaseActivity
import com.check24.productlist.navigation.productsListNavigation
import com.check24.task.R
import com.check24.task.databinding.ActivitySplashBinding


class SplashActivity : BaseActivity<ActivitySplashBinding>() {


    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun getToolbarTitle(): Any?  = null

    override fun onViewAttach() {
        super.onViewAttach()
        navAfterSleep()
    }

    private fun navAfterSleep() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                finish()
                productsListNavigation().startOverViewScreen()
            }, 2000)
    }
}