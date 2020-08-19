package com.r4mste1n.core_db_impl.top_tracks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.r4mste1n.core_db_impl.top_tracks.TopTrackEntity.Companion.TABLE_NAME

/**
 * Created by Alex Shtain on 16.08.2020.
 */
@Entity(tableName = TABLE_NAME)
data class TopTrackEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_SINGER)
    val singer: String? = null,

    @ColumnInfo(name = COLUMN_PHOTO_URL)
    val photoUrl: String? = null,

    @ColumnInfo(name = COLUMN_PLAY_COUNT)
    val playCount: String? = null,

    @ColumnInfo(name = COLUMN_HEARERS_COUNT)
    val hearersCount: String? = null
) {

    companion object {

        const val TABLE_NAME = "TopTracks"

        private const val COLUMN_NAME = "name"
        private const val COLUMN_SINGER = "singer"
        private const val COLUMN_HEARERS_COUNT = "hearers_count"
        private const val COLUMN_PLAY_COUNT = "play_count"
        private const val COLUMN_PHOTO_URL = "photo_url"

    }

}