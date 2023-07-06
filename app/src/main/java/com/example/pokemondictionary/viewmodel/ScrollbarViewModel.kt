package com.example.pokemondictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScrollbarViewModel : ViewModel(){
    private val _scrollBarHeight = MutableLiveData<Int>()
    val scrollBarHeight: LiveData<Int> get() = _scrollBarHeight

    fun setScrollBarHeight(height: Int) {
        _scrollBarHeight.value = height
    }
}