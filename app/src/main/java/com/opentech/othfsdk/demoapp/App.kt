package com.opentech.othfsdk.demoapp

import android.app.Application
import android.content.Context
import android.view.ContextThemeWrapper
import androidx.fragment.app.FragmentActivity
import androidx.multidex.MultiDex
import com.opentech.othfclientsdk.OthfApp
import com.opentech.othfclientsdk.ScaListener

class App : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        val othfFile = BuildConfig.OTHF_LICENSE_FILE
        OthfApp.init(this, object : OthfApp(othfFile) {
            override fun onScaRequested(activity: FragmentActivity, scaPayload: String, listener: ScaListener) {
                /*
				 * 'activity' is an activity managed by OpenPay Bank Plugin. It doesn't use the application theme.
				 * To present e.g. a sheet with the same theme of the hosting application, it is required to use a ContextThemeWrapper as demonstrated below.
				 */
                val appThemedContext: Context = ContextThemeWrapper(activity, R.style.AppTheme)
                performSca(appThemedContext, scaPayload, listener)
            }
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
        OthfApp.getInstance().onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        OthfApp.getInstance().onTrimMemory(level)
    }
}
