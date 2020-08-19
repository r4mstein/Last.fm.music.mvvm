package com.r4mste1n.core_db_impl.top_artists

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.r4mste1n.core_db_impl.top_artists.TopArtistEntity.Companion.TABLE_NAME

/**
 * Created by Alex Shtain on 10.08.2020.
 */
@Dao
interface TopArtistsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artist: TopArtistEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllTopArtists(): LiveData<List<TopArtistEntity>>

    @Query("DELETE FROM $TABLE_NAME")
    fun clearAllTopArtists()

}