package com.r4mste1n.feature_home_impl.starter

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.r4mste1n.feature_home_api.HomeFragmentApi
import com.r4mste1n.feature_home_api.HomeStarterApi
import com.r4mste1n.feature_home_impl.HomeActivity
import com.r4mste1n.feature_home_impl.homefragment.HomeFragment
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

    override fun getHomeFragment(adapterItems: List<Fragment>): HomeFragmentApi =
        HomeFragment.newInstance(adapterItems)

}