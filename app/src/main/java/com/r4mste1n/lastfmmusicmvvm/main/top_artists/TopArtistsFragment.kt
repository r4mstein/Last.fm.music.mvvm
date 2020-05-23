package com.r4mste1n.lastfmmusicmvvm.main.top_artists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists.TopArtistsRepository
import com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists.TopArtistsRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.root.base.BaseFragment

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsFragment : BaseFragment<Contract.ViewModel, Contract.View>() {

    override val viewModel: TopArtistsViewModel by viewModels {
        TopArtistsFactory(TopArtistsRepository())
    }
    override val view = TopArtistsView()
    override val layout: Int = R.layout.fr_top_artists

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topArtists.observe(viewLifecycleOwner, Observer {
            this.view.renderResult(it)
        })
        viewModel.getArtists()
    }

    companion object {
        fun newInstance() = TopArtistsFragment()
    }

}

class TopArtistsFactory(private val repository: TopArtistsRepositoryContract) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TopArtistsViewModel::class.java)) {
            TopArtistsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}