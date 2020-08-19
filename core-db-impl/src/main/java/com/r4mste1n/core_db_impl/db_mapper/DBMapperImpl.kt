package com.r4mste1n.core_db_impl.db_mapper

import com.r4mste1n.core_common.models.Artist
import com.r4mste1n.core_common.models.Track
import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_db_impl.top_artists.TopArtistEntity
import com.r4mste1n.core_db_impl.top_tracks.TopTrackEntity
import javax.inject.Inject

/**
 * Created by Alex Shtain on 15.08.2020.
 */
class DBMapperImpl @Inject constructor() : DBMapperApi {

    override fun convertArtist(artist: Artist): TopArtistEntity? {
        return when {
            artist.name.isNullOrEmpty() -> {
                null
            }
            else -> {
                TopArtistEntity(
                    name = artist.name.orEmpty(),
                    hearersCount = artist.listeners,
                    playCount = artist.stats?.playcount,
                    photoUrl = artist.image?.getOrNull(2)?.text.orEmpty(),
                    url = artist.url
                )
            }
        }
    }

    override fun convertArtistEntities(artists: List<TopArtistEntity>): List<TopArtistData> {
        return ArrayList<TopArtistData>().apply {
            artists.forEach { artist ->
                add(
                    TopArtistData(
                        name = artist.name,
                        hearersCount = artist.hearersCount,
                        playCount = artist.playCount,
                        photoUrl = artist.photoUrl,
                        url = artist.url
                    )
                )
            }
        }
    }

    override fun convertTrack(track: Track): TopTrackEntity? {
        return when {
            track.name.isNullOrEmpty() -> {
                null
            }
            else -> {
                TopTrackEntity(
                    name = track.name.orEmpty(),
                    singer = track.artist?.name,
                    photoUrl = track.image?.getOrNull(2)?.text.orEmpty(),
                    playCount = track.playcount,
                    hearersCount = track.listeners
                )
            }
        }
    }

    override fun convertTrackEntities(tracks: List<TopTrackEntity>): List<TopTrackData> {
        return ArrayList<TopTrackData>().apply {
            tracks.forEach { track ->
                add(
                    TopTrackData(
                        name = track.name,
                        singer = track.singer,
                        photoUrl = track.photoUrl,
                        playCount = track.playCount,
                        hearersCount = track.hearersCount
                    )
                )
            }
        }
    }

}