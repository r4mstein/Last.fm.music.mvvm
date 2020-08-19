package com.r4mste1n.core_db_impl.db_mapper

import com.r4mste1n.core_common.models.Artist
import com.r4mste1n.core_common.models.Track
import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_db_impl.top_artists.TopArtistEntity
import com.r4mste1n.core_db_impl.top_tracks.TopTrackEntity

/**
 * Created by Alex Shtain on 15.08.2020.
 */
interface DBMapperApi {

    fun convertArtist(artist: Artist): TopArtistEntity?

    fun convertArtistEntities(artists: List<TopArtistEntity>): List<TopArtistData>

    fun convertTrack(track: Track): TopTrackEntity?

    fun convertTrackEntities(tracks: List<TopTrackEntity>): List<TopTrackData>

}