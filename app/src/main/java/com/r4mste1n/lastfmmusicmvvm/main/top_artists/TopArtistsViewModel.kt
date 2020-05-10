package com.r4mste1n.lastfmmusicmvvm.main.top_artists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4mste1n.lastfmmusicmvvm.main.repositories.top_artists.TopArtistsRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.adapter.AdapterData
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.models.TopArtistsResponse
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import kotlinx.coroutines.launch

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsViewModel(
    private val repository: TopArtistsRepositoryContract
) : ViewModel(), Contract.ViewModel {

    override val topArtists = MutableLiveData<Result<ArrayList<AdapterData>>>()

    override fun getArtists() {
        viewModelScope.launch {
            repository.loadTopArtists() {
                when (it) {
                    is Result.IsLoading -> topArtists.postValue(it)
                    is Result.Success -> topArtists.postValue(
                        Result.Success(convertLoadedDataToUiData(it.data))
                    )
                    is Result.Error -> topArtists.postValue(it)
                }
            }
        }
    }

    private fun convertLoadedDataToUiData(response: TopArtistsResponse) =
        ArrayList<AdapterData>().apply {
            response.artists?.artist?.forEach {
                add(
                    AdapterData(
                        name = it.name,
                        hearersCount = it.listeners,
                        photoUrl = it.image?.getOrNull(2)?.text ?: ""
                    )
                )
            }
        }

}