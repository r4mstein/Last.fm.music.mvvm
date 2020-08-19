package com.r4mste1n.core_db_impl.top_tracks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Alex Shtain on 16.08.2020.
 */
@Dao
interface TopTracksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: TopTrackEntity)

    @Query("SELECT * FROM ${TopTrackEntity.TABLE_NAME}")
    fun getAllTopTracks(): LiveData<List<TopTrackEntity>>

    @Query("DELETE FROM ${TopTrackEntity.TABLE_NAME}")
    fun clearAllTopTracks()

}