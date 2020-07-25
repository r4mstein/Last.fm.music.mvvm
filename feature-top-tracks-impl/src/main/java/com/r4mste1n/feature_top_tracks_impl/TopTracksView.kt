package com.r4mste1n.feature_top_tracks_impl

import androidx.recyclerview.widget.LinearLayoutManager
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.base.BaseView
import com.r4mste1n.feature_home_api.HomeApi
import com.r4mste1n.feature_top_tracks_impl.adapter.Adapter
import com.r4mste1n.feature_top_tracks_impl.adapter.AdapterData
import kotlinx.android.synthetic.main.fr_top_tracks.view.*
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class TopTracksView @Inject constructor() : BaseView(), Contract.View {

    private lateinit var listAdapter: Adapter

    override fun setupUI() {
        setupList()
    }

    private fun setupList() {
        listAdapter = Adapter()
        rootView.songsList?.apply {
            layoutManager = LinearLayoutManager(rootView.context)
            adapter = listAdapter
        }
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