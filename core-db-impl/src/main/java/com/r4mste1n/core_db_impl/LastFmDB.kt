package com.r4mste1n.core_db_impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.r4mste1n.core_db_impl.top_artists.TopArtistEntity
import com.r4mste1n.core_db_impl.top_artists.TopArtistsDAO
import com.r4mste1n.core_db_impl.top_tracks.TopTrackEntity
import com.r4mste1n.core_db_impl.top_tracks.TopTracksDAO

/**
 * Created by Alex Shtain on 10.08.2020.
 */
@Database(
    entities = [
        TopArtistEntity::class,
        TopTrackEntity::class
    ],
    version = 1
)
abstract class LastFmDB : RoomDatabase() {

    abstract val topArtists: TopArtistsDAO
    abstract val topTracks: TopTracksDAO

    companion object {

        private lateinit var instance: LastFmDB

        @Synchronized
        fun getInstance(context: Context): LastFmDB {
            return when {
                !::instance.isInitialized -> {
                    instance = Room.databaseBuilder(
                        context,
                        LastFmDB::class.java,
                        LastFmDB::class.java.simpleName
                    ).build()

                    instance
                }
                else -> {
                    instance
                }
            }
        }

    }

}