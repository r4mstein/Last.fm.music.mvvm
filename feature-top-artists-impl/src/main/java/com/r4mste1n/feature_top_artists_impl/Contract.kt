package com.r4mste1n.feature_top_artists_impl

import androidx.lifecycle.LiveData
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.base.ViewContract
import com.r4mste1n.core_common.base.ViewModelContract
import com.r4mste1n.feature_top_artists_impl.adapter.AdapterData

/**
 * Created by Alex Shtain on 09.05.2020.
 */
interface Contract {

    interface View : ViewContract {

        fun renderResult(result: Result<ArrayList<AdapterData>>)

    }

    interface ViewModel : ViewModelContract {

        val topArtists: LiveData<Result<ArrayList<AdapterData>>>

        fun getArtists()

    }

}