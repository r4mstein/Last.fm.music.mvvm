package com.r4mste1n.feature_top_artists_impl

import androidx.recyclerview.widget.LinearLayoutManager
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.base.BaseView
import com.r4mste1n.feature_home_api.HomeApi
import com.r4mste1n.feature_top_artists_impl.adapter.Adapter
import com.r4mste1n.feature_top_artists_impl.adapter.AdapterData
import kotlinx.android.synthetic.main.fr_top_artists.view.*
import javax.inject.Inject

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsView @Inject constructor() : BaseView(), Contract.View {

    private lateinit var listAdapter: Adapter

    override fun setupUI() {
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
        (context as HomeApi).showArtistInfoFragment(it.name ?: "")
    }

    override fun renderResult(result: Result<ArrayList<AdapterData>>) {
        when (result) {
            is Result.IsLoading -> renderLoading()
            is Result.Success -> renderSuccess(result.data)
            is Result.Error -> renderError(result.message)
        }
    }

    private fun renderLoading() {
        (context as HomeApi).showProgressBar()
    }

    private fun renderSuccess(data: List<AdapterData>) {
        listAdapter.setData(data)
        (context as HomeApi).hideProgressBar()
    }

    private fun renderError(message: String?) {
        (context as HomeApi).apply {
            showError(message ?: context.getString(R.string.default_error_message))
            hideProgressBar()
        }
    }

}