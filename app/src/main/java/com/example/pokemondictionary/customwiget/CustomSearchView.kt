package com.example.pokemondictionary.customwiget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView

class CustomSearchView : SearchView {

    lateinit var searchSrcTextView: SearchView.SearchAutoComplete

    private var queryTextListener: OnQueryTextListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )



    override fun setOnQueryTextListener(listener: OnQueryTextListener?) {
        super.setOnQueryTextListener(listener)
        queryTextListener = listener

        searchSrcTextView = this.findViewById(androidx.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete
        searchSrcTextView.setOnEditorActionListener { _, _, _ ->
            listener?.onQueryTextSubmit(query.toString())
            true
        }
    }
}