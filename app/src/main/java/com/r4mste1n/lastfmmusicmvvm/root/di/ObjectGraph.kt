package com.r4mste1n.lastfmmusicmvvm.root.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.r4mste1n.core_db_impl.di.DiDBComponent
import com.r4mste1n.core_network_impl.di.DiNetworkComponent
import com.r4mste1n.core_repositories_impl.di.DaggerDiRepositoriesComponent_DiRepositoriesDependenciesComponent
import com.r4mste1n.core_repositories_impl.di.DiRepositoriesComponent
import com.r4mste1n.feature_artist_info_impl.di.DaggerDiArtistInfoComponent_DiArtistInfoDependenciesComponent
import com.r4mste1n.feature_artist_info_impl.di.DiArtistInfoComponent
import com.r4mste1n.feature_home_impl.di.DaggerDiHomeComponent_DiHomeDependenciesComponent
import com.r4mste1n.feature_home_impl.di.DiHomeComponent
import com.r4mste1n.feature_top_artists_impl.di.DaggerDiTopArtistsComponent_DiTopArtistsDependenciesComponent
import com.r4mste1n.feature_top_artists_impl.di.DiTopArtistsComponent
import com.r4mste1n.feature_top_tracks_impl.di.DaggerDiTopTracksComponent_DiTopTracksDependenciesComponent
import com.r4mste1n.feature_top_tracks_impl.di.DiTopTracksComponent
import com.r4mste1n.lastfmmusicmvvm.root.NavigatorImpl

/**
 * Created by Alex Shtain on 23.05.2020.
 */
object ObjectGraph {

    private lateinit var context: Context

    private var repoComponent: DiRepositoriesComponent? = null
    private var dbComponent: DiDBComponent? = null

    fun initComponents(context: Context) {

        this.context = context

        dbComponent = getDBComponent(context)
        repoComponent = getRepositoriesComponent()

        initHomeComponent()

    }

    private fun initHomeComponent() {
        DiHomeComponent.initAndGet(
            DaggerDiHomeComponent_DiHomeDependenciesComponent.builder()
                .navigatorApi(NavigatorImpl())
                .build()
        )
    }

    fun getHomeFragment(adapterItems: List<Fragment>) =
        DiHomeComponent.get().homeStarterApi().getHomeFragment(adapterItems)

    fun getArtistInfo() = DiArtistInfoComponent.initAndGet(
        DaggerDiArtistInfoComponent_DiArtistInfoDependenciesComponent.builder()
            .artistInfoRepoApi(getRepositoriesComponent().artistInfoRepoApi())
            .build()
    )

    fun getTopArtists() = DiTopArtistsComponent.initAndGet(
        DaggerDiTopArtistsComponent_DiTopArtistsDependenciesComponent.builder()
            .topArtistsRepoApi(getRepositoriesComponent().topArtistsRepoApi())
            .build()
    )

    fun getTopTracks() = DiTopTracksComponent.initAndGet(
        DaggerDiTopTracksComponent_DiTopTracksDependenciesComponent.builder()
            .topTracksRepoApi(getRepositoriesComponent().topTracksRepoApi())
            .build()
    )

    private fun getRepositoriesComponent() = repoComponent ?: DiRepositoriesComponent.get(
        DaggerDiRepositoriesComponent_DiRepositoriesDependenciesComponent.builder()
            .errorUtilsApi(DiNetworkComponent.get().errorUtilsApi())
            .httpClientApi(DiNetworkComponent.get().httpClientApi())
            .dBManagerApi(getDBComponent(context).dbManagerApi())
            .build()
    )

    private fun getDBComponent(context: Context) = dbComponent ?: DiDBComponent.get(context)

}