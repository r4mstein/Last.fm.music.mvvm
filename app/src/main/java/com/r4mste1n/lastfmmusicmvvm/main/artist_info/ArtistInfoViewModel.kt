package com.r4mste1n.lastfmmusicmvvm.main.artist_info

import androidx.lifecycle.*
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.ArtistInfoResponse
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.UiData
import com.r4mste1n.lastfmmusicmvvm.main.models.Artist
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alex Shtain on 03.05.2020.
 */
class ArtistInfoViewModel @Inject constructor(
    private val repository: ArtistInfoRepositoryContract
) : ViewModel(), Contract.ViewModel {

    private val _artistInfo = MutableLiveData<Result<UiData>>()

    override val artistInfo: LiveData<Result<UiData>>
        get() = Transformations.switchMap(repository.artistInfo) {

            _artistInfo.postValue(getUiResult(it))

            return@switchMap _artistInfo
        }

    private fun getUiResult(result: Result<ArtistInfoResponse>): Result<UiData> {
        return when (result) {
            is Result.IsLoading -> {
                Result.IsLoading
            }
            is Result.Success -> {
                Result.Success(convertLoadedDataToUiData(result.data.artist))
            }
            is Result.Error -> {
                Result.Error(result.message)
            }
        }
    }

    override fun loadArtistInfo(artistName: String) {
        viewModelScope.launch {
            repository.loadArtistInfo(artistName)
        }
    }

    private fun convertLoadedDataToUiData(artist: Artist?) = UiData(
        artistPhoto = artist?.image?.getOrNull(2)?.text ?: "",
        artistName = artist?.name ?: "",
        artistTags = artist?.tags?.tag?.let {
            val uiTags = ArrayList<String>(artist.tags.tag.size)

            it.forEach { tag ->
                tag.name?.let { tagName -> uiTags.add(tagName) }
            }

            uiTags
        } ?: emptyList(),
        hearersCount = artist?.stats?.listeners ?: "",
        playCount = artist?.stats?.playcount ?: "",
        bio = artist?.bio?.content ?: "",
        bioPublished = artist?.bio?.published ?: ""
    )

}