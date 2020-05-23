package com.r4mste1n.lastfmmusicmvvm.main.artist_info

import androidx.lifecycle.*
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.ArtistInfoResponse
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.UiData
import com.r4mste1n.lastfmmusicmvvm.main.models.Artist
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import kotlinx.coroutines.launch

/**
 * Created by Alex Shtain on 03.05.2020.
 */
class ArtistInfoViewModel(
    private val repository: ArtistInfoRepositoryContract
) : ViewModel(), Contract.ViewModel {

    private val _artistInfo = MutableLiveData<Result<UiData>>()

    override val artistInfo: LiveData<Result<UiData>>
        get() = Transformations.switchMap(repository.artistInfo) {

            _artistInfo.postValue(getUiResult(it))

            return@switchMap _artistInfo
        }

    private fun getUiResult(it: Result<ArtistInfoResponse>): Result<UiData> {
        return when (it) {
            is Result.IsLoading -> {
                Result.IsLoading
            }
            is Result.Success -> {
                Result.Success(convertLoadedDataToUiData(it.data.artist))
            }
            is Result.Error -> {
                Result.Error(it.message)
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