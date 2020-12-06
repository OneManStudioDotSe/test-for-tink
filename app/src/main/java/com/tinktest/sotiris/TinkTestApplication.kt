package com.tinktest.sotiris

import android.content.Context
import androidx.multidex.MultiDexApplication
import timber.log.Timber

/**
 * Created by Sotiris Falieris (sotiris@onemanstudio.se) on 2020-05-28.
 */
class TinkTestApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initializeModules() //initialize the tools that we use in the app
    }

    private fun initializeModules() {
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.d("The application was created")
    }

    companion object {
        private var instance: TinkTestApplication? = null
        val context: Context
            get() = instance!!.applicationContext
    }
}

