package com.r4mste1n.lastfmmusicmvvm.main.top_artists

import androidx.recyclerview.widget.LinearLayoutManager
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.main.MainActivity
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.adapter.Adapter
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.adapter.AdapterData
import com.r4mste1n.lastfmmusicmvvm.root.base.BaseView
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import kotlinx.android.synthetic.main.fr_top_artists.view.*
import javax.inject.Inject

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsView @Inject constructor() : BaseView(), Contract.View {

    private lateinit var listAdapter: Adapter

    override fun setupUI() {
        (context as MainActivity).setToolbarTitle(context.getString(R.string.top_artists_toolbar_title))
        setupList()
    }

    private fun setupList() {
        listAdapter = Adapter(clickListener)
        rootView.artistsList?.apply {
            layoutManager = LinearLayoutManager(rootView.context)
            adapter = listAdapter
        }
    }

    private val clickListener: (item: AdapterData) -> Unit = {
        (context as MainActivity).showArtistInfo(it.name ?: "")
    }

    override fun renderResult(result: Result<ArrayList<AdapterData>>) {
        when (result) {
            is Result.IsLoading -> renderLoading()
            is Result.Success -> renderSuccess(result.data)
            is Result.Error -> renderError(result.message)
        }
    }

    private fun renderLoading() {
        (context as MainActivity).showProgressBar()
    }

    private fun renderSuccess(data: List<AdapterData>) {
        listAdapter.setData(data)
        (context as MainActivity).hideProgressBar()
    }

    private fun renderError(message: String?) {
        (context as MainActivity).apply {
            showError(message ?: context.getString(R.string.default_error_message))
            hideProgressBar()
        }
    }

}