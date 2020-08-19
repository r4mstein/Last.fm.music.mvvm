package com.r4mste1n.feature_top_artists_impl

import androidx.lifecycle.*
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_repositories_api.TopArtistsRepoApi
import com.r4mste1n.feature_top_artists_impl.adapter.AdapterData
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alex Shtain on 02.05.2020.
 */
class TopArtistsViewModel @Inject constructor(
    private val repository: TopArtistsRepoApi
) : ViewModel(), Contract.ViewModel {

    private val _topArtists = MutableLiveData<Result<ArrayList<AdapterData>>>()

    override val topArtists: LiveData<Result<ArrayList<AdapterData>>>
        get() = Transformations.switchMap(repository.topArtists) {

            _topArtists.postValue(getUiResult(it))

            return@switchMap _topArtists
        }

    private fun getUiResult(result: Result<List<TopArtistData>>): Result<ArrayList<AdapterData>> {
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

    private fun convertLoadedDataToUiData(response: List<TopArtistData>) =
        ArrayList<AdapterData>().apply {
            response.forEach {
                add(
                    AdapterData(
                        name = it.name,
                        hearersCount = it.hearersCount,
                        photoUrl = it.photoUrl
                    )
                )
            }
        }

}