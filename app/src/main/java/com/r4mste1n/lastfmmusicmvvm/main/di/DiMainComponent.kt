package com.r4mste1n.lastfmmusicmvvm.main.di

import com.r4mste1n.lastfmmusicmvvm.main.MainActivity
import com.r4mste1n.lastfmmusicmvvm.main.artist_info.ArtistInfoFragment
import com.r4mste1n.lastfmmusicmvvm.main.repositories.di.DiMainRepositoriesModule
import com.r4mste1n.lastfmmusicmvvm.main.top_artists.TopArtistsFragment
import com.r4mste1n.lastfmmusicmvvm.root.di.DiAppComponent
import dagger.Component

/**
 * Created by Alex Shtain on 23.05.2020.
 */
@MainScope
@Component(
    modules = [
        DiMainModule::class,
        DiMainViewModelModule::class,
        DiMainRepositoriesModule::class
    ],
    dependencies = [DiAppComponent::class]
)
interface DiMainComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: TopArtistsFragment)

    fun inject(fragment: ArtistInfoFragment)

}