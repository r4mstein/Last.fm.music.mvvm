package com.r4mste1n.lastfmmusicmvvm.root.base

import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Created by Alex Shtain on 09.05.2020.
 */
abstract class BaseView : ViewContract {

    protected lateinit var rootView: View
    protected lateinit var context: Context

    abstract fun setupUI()

    override fun setContentView(rootView: View) {
        this.rootView = rootView
        context = rootView.context
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView() {
        setupUI()
    }

    override fun onViewCreated(bundle: Bundle?) {

    }

}