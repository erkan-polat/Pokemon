package com.example.kotlinpokemon.Interface
import android.view.View

interface IItemClientListener {
    fun onClickView(view: View, position: Int)
}