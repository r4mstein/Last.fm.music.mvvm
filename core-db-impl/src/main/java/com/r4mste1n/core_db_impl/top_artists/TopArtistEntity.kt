package com.r4mste1n.core_db_impl.top_artists

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.r4mste1n.core_db_impl.top_artists.TopArtistEntity.Companion.TABLE_NAME

/**
 * Created by Alex Shtain on 10.08.2020.
 */
@Entity(tableName = TABLE_NAME)
data class TopArtistEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_HEARERS_COUNT)
    val hearersCount: String? = null,

    @ColumnInfo(name = COLUMN_PLAY_COUNT)
    val playCount: String? = null,

    @ColumnInfo(name = COLUMN_PHOTO_URL)
    val photoUrl: String? = null,

    @ColumnInfo(name = COLUMN_URL)
    val url: String? = null
) {

    companion object {

        const val TABLE_NAME = "TopArtists"

        private const val COLUMN_NAME = "name"
        private const val COLUMN_HEARERS_COUNT = "hearers_count"
        private const val COLUMN_PLAY_COUNT = "play_count"
        private const val COLUMN_PHOTO_URL = "photo_url"
        private const val COLUMN_URL = "url"

    }

}