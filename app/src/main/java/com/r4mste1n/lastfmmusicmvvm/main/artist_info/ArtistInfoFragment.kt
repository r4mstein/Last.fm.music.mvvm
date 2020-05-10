package com.r4mste1n.lastfmmusicmvvm.main.artist_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.r4mste1n.lastfmmusicmvvm.R
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepository
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.root.base.BaseFragment

/**
 * Created by Alex Shtain on 03.05.2020.
 */
class ArtistInfoFragment : BaseFragment<Contract.ViewModel, Contract.View>() {

    override val viewModel: ArtistInfoViewModel by viewModels {
        ArtistInfoFactory(ArtistInfoRepository())
    }
    override val view: ArtistInfoView = ArtistInfoView()
    override val layout: Int = R.layout.fr_artist_info

    companion object {

        const val ARTIST_NAME_KEY = "artist_name_key"

        fun newInstance(artistName: String) = ArtistInfoFragment().apply {
            arguments = Bundle().apply {
                putString(ARTIST_NAME_KEY, artistName)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.artistInfo.observe(viewLifecycleOwner, Observer {
            this.view.renderResult(it)
        })
    }

}

class ArtistInfoFactory(private val repository: ArtistInfoRepositoryContract) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ArtistInfoViewModel::class.java)) {
            ArtistInfoViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}