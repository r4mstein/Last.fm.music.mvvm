package com.r4mste1n.lastfmmusicmvvm.main.top_artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.root.base.BaseFragment
import com.r4mste1n.lastfmmusicmvvm.root.di.ObjectGraph
import javax.inject.Inject

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsFragment : BaseFragment<Contract.ViewModel, Contract.View>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val viewModel: TopArtistsViewModel by viewModels() {
        factory
    }

    @Inject
    override lateinit var view: TopArtistsView
    override val layout: Int = R.layout.fr_top_artists

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ObjectGraph.getMainComponent()?.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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