package com.r4mste1n.feature_top_tracks_impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.core_common.base.BaseFragment
import com.r4mste1n.feature_top_tracks_api.TopTracksApi
import com.r4mste1n.feature_top_tracks_impl.di.DiTopTracksComponent
import javax.inject.Inject

/**
 * Created by Alex Shtain on 22.07.2020.
 */
class TopTracksFragment : BaseFragment<Contract.ViewModel, Contract.View>(), TopTracksApi {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val viewModel: TopTracksViewModel by viewModels() {
        factory
    }

    @Inject
    override lateinit var view: TopTracksView
    override val layout: Int = R.layout.fr_top_tracks

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DiTopTracksComponent.get().inject(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topTracks.observe(viewLifecycleOwner, Observer {
            this.view.renderResult(it)
        })
        viewModel.getTracks()
    }

    companion object {

        fun newInstance() = TopTracksFragment()

    }

}