package com.r4mste1n.core_common.base

import android.os.Bundle
import android.view.View

/**
 * Created by Alex Shtain on 09.05.2020.
 */
interface ViewContract {

    fun setContentView(rootView: View)

    fun onCreateView()

    fun onViewCreated(bundle: Bundle?)

}