package com.r4mste1n.feature_top_tracks_impl

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.base.ViewContract
import com.r4mste1n.core_common.base.ViewModelContract
import com.r4mste1n.feature_top_tracks_impl.adapter.AdapterData

/**
 * Created by Alex Shtain on 22.07.2020.
 */
interface Contract {

    interface View : ViewContract {

        fun renderResult(result: Result<ArrayList<AdapterData>>)

    }

    interface ViewModel : ViewModelContract {

        val topTracks: LiveData<Result<ArrayList<AdapterData>>>

        fun getTracks()

    }

}