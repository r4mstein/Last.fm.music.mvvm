package com.r4mste1n.core_common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Alex Shtain on 05.07.2020.
 */
fun AppCompatActivity?.addFragment(containerId: Int, fragment: Fragment) {
    this?.supportFragmentManager
        ?.beginTransaction()
        ?.add(containerId, fragment)
        ?.commit()
}

fun AppCompatActivity?.replaceFragment(containerId: Int, fragment: Fragment) {
    this?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(containerId, fragment)
        ?.commit()
}

fun AppCompatActivity?.replaceFragmentAndAddToBackStack(containerId: Int, fragment: Fragment) {
    this?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(containerId, fragment)
        ?.addToBackStack(null)
        ?.commit()
}