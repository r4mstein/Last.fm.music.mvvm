package com.r4mste1n.lastfmmusicmvvm.main.artist_info

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
 * Created by Alex Shtain on 03.05.2020.
 */
class ArtistInfoFragment : BaseFragment<Contract.ViewModel, Contract.View>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val viewModel: ArtistInfoViewModel by viewModels() {
        factory
    }

    @Inject
    override lateinit var view: ArtistInfoView
    override val layout: Int = R.layout.fr_artist_info

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ObjectGraph.getMainComponent().inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.artistInfo.observe(viewLifecycleOwner, Observer {
            this.view.renderResult(it)
        })
        viewModel.loadArtistInfo(arguments?.getString(ARG_ARTIST_NAME).orEmpty())
    }

    companion object {

        const val ARG_ARTIST_NAME = "arg_artist_name"

        fun newInstance(artistName: String) = ArtistInfoFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_ARTIST_NAME, artistName)
            }
        }
    }

}