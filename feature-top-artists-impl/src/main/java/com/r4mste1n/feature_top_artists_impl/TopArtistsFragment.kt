package com.r4mste1n.feature_top_artists_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.core_common.base.BaseFragment
import com.r4mste1n.feature_top_artists_api.TopArtistsApi
import com.r4mste1n.feature_top_artists_impl.di.DiTopArtistsComponent
import javax.inject.Inject

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsFragment : BaseFragment<Contract.ViewModel, Contract.View>(), TopArtistsApi {

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
        DiTopArtistsComponent.get().inject(this)

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