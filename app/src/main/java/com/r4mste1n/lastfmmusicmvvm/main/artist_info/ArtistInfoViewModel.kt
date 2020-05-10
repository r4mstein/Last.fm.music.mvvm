package com.r4mste1n.lastfmmusicmvvm.main.artist_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.Artist
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.UiData
import com.r4mste1n.lastfmmusicmvvm.main.repositories.artist_info.ArtistInfoRepositoryContract
import com.r4mste1n.lastfmmusicmvvm.root.network.Result
import kotlinx.coroutines.launch

/**
 * Created by Alex Shtain on 03.05.2020.
 */
class ArtistInfoViewModel(
    private val repository: ArtistInfoRepositoryContract
) : ViewModel(), Contract.ViewModel {

    override val artistInfo = MutableLiveData<Result<UiData>>()

    override fun loadArtistInfo(artistName: String) {
        viewModelScope.launch {
            repository.loadArtistInfo(artistName) {
                when (it) {
                    is Result.IsLoading -> artistInfo.postValue(it)
                    is Result.Success -> artistInfo.postValue(
                        Result.Success(convertLoadedDataToUiData(it.data.artist))
                    )
                    is Result.Error -> artistInfo.postValue(it)
                }
            }
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