package com.r4mste1n.feature_home_impl.starter

import android.content.Context
import android.content.Intent
import com.r4mste1n.feature_home_api.HomeStarterApi
import com.r4mste1n.feature_home_impl.HomeActivity
import javax.inject.Inject

/**
 * Created by Alex Shtain on 29.06.2020.
 */
class HomeStarterImpl @Inject constructor() : HomeStarterApi {

    override fun start(context: Context) {
        context.startActivity(
            Intent(context, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }

}