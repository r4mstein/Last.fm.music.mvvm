package com.r4mste1n.feature_artist_info_impl

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.base.ViewContract
import com.r4mste1n.core_common.base.ViewModelContract
import com.r4mste1n.feature_artist_info_impl.models.UiData

/**
 * Created by Alex Shtain on 09.05.2020.
 */
interface Contract {

    interface View : ViewContract {

        fun renderResult(result: Result<UiData>)

    }

    interface ViewModel : ViewModelContract {

        val artistInfo: LiveData<Result<UiData>>

        fun loadArtistInfo(artistName: String)

    }

}