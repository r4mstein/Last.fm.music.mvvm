package com.r4mste1n.core_repositories_impl.repo_mapper

import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_common.models.responses.TopArtistsResponse
import com.r4mste1n.core_common.models.responses.TopTracksResponse
import javax.inject.Inject

/**
 * Created by Alex Shtain on 16.08.2020.
 */
class RepoMapperImpl @Inject constructor() : RepoMapperApi {

    override fun convertTopArtists(artists: TopArtistsResponse): List<TopArtistData> {
        return ArrayList<TopArtistData>().apply {
            artists.artists?.artist?.forEach { artist ->
                add(
                    TopArtistData(
                        name = artist.name.orEmpty(),
                        hearersCount = artist.listeners,
                        playCount = artist.stats?.playcount,
                        photoUrl = artist.image?.getOrNull(2)?.text.orEmpty(),
                        url = artist.url
                    )
                )
            }
        }
    }

    override fun convertTopTracks(tracks: TopTracksResponse): List<TopTrackData> {
        return ArrayList<TopTrackData>().apply {
            tracks.tracks?.track?.forEach { track ->
                add(
                    TopTrackData(
                        name = track.name.orEmpty(),
                        singer = track.artist?.name,
                        photoUrl = track.image?.getOrNull(2)?.text,
                        playCount = track.playcount,
                        hearersCount = track.listeners
                    )
                )
            }
        }
    }
}