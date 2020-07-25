package com.r4mste1n.feature_home_api

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Created by Alex Shtain on 29.06.2020.
 */
interface HomeStarterApi {

    fun start(context: Context)

    fun getHomeFragment(adapterItems: List<Fragment>): HomeFragmentApi

}