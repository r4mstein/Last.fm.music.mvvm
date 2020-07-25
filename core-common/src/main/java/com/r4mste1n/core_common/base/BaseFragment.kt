package com.r4mste1n.core_common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Alex Shtain on 09.05.2020.
 */
abstract class BaseFragment<VM : ViewModelContract, V : ViewContract> : Fragment() {

    protected abstract val layout: Int
    protected abstract val view: V
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false).also {
        view.setContentView(it)
        view.onCreateView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.view.onViewCreated(arguments)
    }

}