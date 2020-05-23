package com.r4mste1n.lastfmmusicmvvm.main.top_artists

import androidx.lifecycle.LiveData
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.adapter.AdapterData
import com.r4mste1n.lastfmmusicmvvm.root.base.ViewContract
import com.r4mste1n.lastfmmusicmvvm.root.base.ViewModelContract
import com.r4mste1n.lastfmmusicmvvm.root.network.Result

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