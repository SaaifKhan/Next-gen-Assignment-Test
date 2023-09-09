package com.saif.base.extensions

import android.view.View


infix fun View?.show(show: Boolean) {
    if (show)
        this?.visibility = View.VISIBLE
    else
        this?.visibility = View.GONE
}