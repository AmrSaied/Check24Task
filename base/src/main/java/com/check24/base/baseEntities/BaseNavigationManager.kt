package com.check24.base.baseEntities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils

/**
 * A navigation helper class with some intents nav useful functions
 */
open class BaseNavigationManager {

    fun start(clazz: Class<out AppCompatActivity>, clearStack: Boolean = false) {
        if (clearStack)
            ActivityUtils.finishAllActivities()
        ActivityUtils.startActivity(clazz)
    }

    fun startWithBundle(clazz: Class<out AppCompatActivity>, bundle: Bundle) {
        ActivityUtils.startActivity(bundle, clazz)
    }


    private fun startWithBundle(clazz: Class<out AppCompatActivity>, bundle: Bundle, clearStack: Boolean = false) {
        if (clearStack)
            ActivityUtils.finishAllActivities()
        ActivityUtils.startActivity(bundle, clazz)
    }

    companion object{
        const val KEY_OBJECT = "object"
    }

}