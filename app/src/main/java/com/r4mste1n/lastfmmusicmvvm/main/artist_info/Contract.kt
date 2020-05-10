package com.r4mste1n.lastfmmusicmvvm.main.artist_info

import androidx.lifecycle.MutableLiveData
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.models.UiData
import com.r4mste1n.lastfmmusicmvvm.root.base.ViewContract
import com.r4mste1n.lastfmmusicmvvm.root.base.ViewModelContract
import com.r4mste1n.lastfmmusicmvvm.root.network.Result

/**
 * Created by Alex Shtain on 09.05.2020.
 */
interface Contract {

    interface View : ViewContract {

        fun renderResult(result: Result<UiData>)

    }

    interface ViewModel : ViewModelContract {

        val artistInfo: MutableLiveData<Result<UiData>>

        fun loadArtistInfo(artistName: String)

    }

}