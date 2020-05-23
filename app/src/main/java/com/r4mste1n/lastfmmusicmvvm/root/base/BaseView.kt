package com.r4mste1n.lastfmmusicmvvm.root.base

import android.content.Context
import android.os.Bundle
import android.view.View

/**
 * Created by Alex Shtain on 09.05.2020.
 */
abstract class BaseView<VM : ViewModelContract> : ViewContract {

    protected lateinit var rootView: View
    protected lateinit var context: Context
    protected lateinit var viewModel: VM

    abstract fun setupUI()

    override fun setContentView(rootView: View) {
        this.rootView = rootView
        context = rootView.context
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(viewModel: ViewModelContract) {
        this.viewModel = viewModel as VM
        setupUI()
    }

    override fun onViewCreated(bundle: Bundle?) {

    }

}