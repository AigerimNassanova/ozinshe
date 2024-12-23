package com.example.ozinshe.domain.utils

import androidx.fragment.app.Fragment
import com.example.ozinshe.domain.utils.NavigationHostProvider

fun Fragment.provideNavigationHost(): NavigationHostProvider? {
    return try {
        activity as NavigationHostProvider
    } catch (e: Exception) {
        null
    }
}