package com.check24.base.baseEntities

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.check24.base.R
import com.gturedi.views.StatefulLayout


/**
 *
 * Parent of all activities , the generic [DB] is used to to use data binding of the desired type directly
 */
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(){




    @LayoutRes
    abstract fun getLayoutRes(): Int


    lateinit var mBinding : DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding =  DataBindingUtil.setContentView(this, getLayoutRes()) as DB

        initViewModel()

        setGenericToolBar()
        onViewAttach()

    }


    open fun initViewModel(){

    }


    override fun onStart() {
        super.onStart()
        onViewRefresh()
    }

    //OnStart
    open fun onViewRefresh() {

    }

    //OnCreate
    open fun onViewAttach() {

    }

    abstract fun getToolbarTitle(): Any?


    private fun setGenericToolBar() {

        val title = getToolbarTitle()


        title?.let {

            val toolbar = mBinding.root.findViewById<Toolbar>(R.id.toolbar)

            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val titleTxt = when (it) {
                is String -> it
                is Int -> resources.getString(it)
                else -> throw UnsupportedOperationException("Title must be a string resource or a plain string")
            }

            supportActionBar?.title = titleTxt
        }
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    fun showLoading(stateFullLayout: StatefulLayout) {
        stateFullLayout.showLoading()
    }

    fun showEmpty(stateFullLayout: StatefulLayout) {
        stateFullLayout.showEmpty()
    }

    fun showError(stateFullLayout: StatefulLayout, error:String?, retry :()->Unit) {
        stateFullLayout.showError(error?:getString(R.string.something_went_wrong)){retry()}
    }

    fun showContent(stateFullLayout: StatefulLayout) {
        stateFullLayout.showContent()
    }


}