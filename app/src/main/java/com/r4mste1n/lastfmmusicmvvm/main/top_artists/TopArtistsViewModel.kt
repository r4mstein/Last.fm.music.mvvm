package com.r4mste1n.lastfmmusicmvvm.main.top_artists

import androidx.lifecycle.*
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

    private val _topArtists = MutableLiveData<Result<ArrayList<AdapterData>>>()

    override val topArtists: LiveData<Result<ArrayList<AdapterData>>>
        get() = Transformations.switchMap(repository.topArtists) {

            _topArtists.postValue(getUiResult(it))

            return@switchMap _topArtists
        }

    private fun getUiResult(result: Result<TopArtistsResponse>): Result<ArrayList<AdapterData>> {
        return when (result) {
            is Result.IsLoading -> {
                Result.IsLoading
            }
            is Result.Error -> {
                Result.Error(result.message)
            }
            is Result.Success -> {
                Result.Success(convertLoadedDataToUiData(result.data))
            }
        }
    }

    override fun getArtists() {
        viewModelScope.launch {
            repository.getTopArtists()
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