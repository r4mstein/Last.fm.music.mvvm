package com.r4mste1n.core_common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Alex Shtain on 09.05.2020.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setupUI()
    }

    abstract fun setupUI()
}