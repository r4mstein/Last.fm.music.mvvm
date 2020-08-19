package com.r4mste1n.core_db_impl.db_manager

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.r4mste1n.core_common.models.data.TopArtistData
import com.r4mste1n.core_common.models.data.TopTrackData
import com.r4mste1n.core_common.models.responses.TopArtistsResponse
import com.r4mste1n.core_common.models.responses.TopTracksResponse
import com.r4mste1n.core_db_api.DBManagerApi
import com.r4mste1n.core_db_impl.LastFmDB
import com.r4mste1n.core_db_impl.db_mapper.DBMapperApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Alex Shtain on 12.08.2020.
 */
class DBManagerImpl @Inject constructor(
    private val context: Context,
    private val dbMapper: DBMapperApi
) : DBManagerApi {

    override suspend fun insertTopArtists(data: TopArtistsResponse) = withContext(Dispatchers.IO) {
        data.artists?.artist?.let { artists ->

            artists.forEach { artist ->
                dbMapper.convertArtist(artist)?.let { artistEntity ->
                    LastFmDB.getInstance(context)
                        .topArtists
                        .insert(artistEntity)
                }
            }

            return@withContext true
        } ?: let {
            return@withContext false
        }
    }

    override suspend fun getTopArtists(): LiveData<List<TopArtistData>> =
        withContext(Dispatchers.IO) {
            Transformations.switchMap(LastFmDB.getInstance(context).topArtists.getAllTopArtists()) { artists ->

                MutableLiveData<List<TopArtistData>>(dbMapper.convertArtistEntities(artists))
            }
        }

    override suspend fun clearAllTopArtists() = withContext(Dispatchers.IO) {
        LastFmDB.getInstance(context).topArtists.clearAllTopArtists()
    }

    override suspend fun insertTopTracks(data: TopTracksResponse) = withContext(Dispatchers.IO) {
        data.tracks?.track?.let { tracks ->

            tracks.forEach { track ->
                dbMapper.convertTrack(track)?.let { trackEntity ->
                    LastFmDB.getInstance(context)
                        .topTracks
                        .insert(trackEntity)
                }
            }

            return@withContext true
        } ?: let {
            return@withContext false
        }
    }

    override suspend fun getTopTracks(): LiveData<List<TopTrackData>> {
        return Transformations.switchMap(LastFmDB.getInstance(context).topTracks.getAllTopTracks()) { tracks ->

            MutableLiveData<List<TopTrackData>>(dbMapper.convertTrackEntities(tracks))
        }
    }

    override suspend fun clearAllTopTracks() = withContext(Dispatchers.IO) {
        LastFmDB.getInstance(context).topTracks.clearAllTopTracks()
    }

}