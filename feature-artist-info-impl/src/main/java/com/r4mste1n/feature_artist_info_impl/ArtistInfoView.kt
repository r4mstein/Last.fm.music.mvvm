package com.r4mste1n.feature_artist_info_impl

import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.chip.Chip
import com.r4mste1n.core_common.Result
import com.r4mste1n.core_common.base.BaseView
import com.r4mste1n.core_common.extensions.ImageTransformType
import com.r4mste1n.core_common.extensions.dpToPix
import com.r4mste1n.core_common.extensions.formatCount
import com.r4mste1n.core_common.extensions.loadImage
import com.r4mste1n.feature_artist_info_impl.models.UiData
import com.r4mste1n.feature_home_api.HomeApi
import kotlinx.android.synthetic.main.fr_artist_info.view.*
import javax.inject.Inject

/**
 * Created by Alex Shtain on 03.05.2020.
 */
class ArtistInfoView @Inject constructor() : BaseView(), Contract.View {

    override fun setupUI() {
        (context as HomeApi).setToolbarTitle(context.getString(R.string.artist_info_toolbar_title))
    }

    override fun renderResult(result: Result<UiData>) {
        when (result) {
            is Result.IsLoading -> renderLoading()
            is Result.Success -> renderSuccess(result.data)
            is Result.Error -> renderError(result.message)
        }
    }

    private fun renderLoading() {
        (context as HomeApi).showProgressBar()
    }

    private fun renderSuccess(uiData: UiData) {
        setArtistPhoto(uiData.artistPhoto)
        setArtistName(uiData.artistName)
        setArtistTags(uiData.artistTags)
        setHearersCount(uiData.hearersCount)
        setPlayCount(uiData.playCount)
        setBio(uiData.bio)
        setBioPublished(uiData.bioPublished)

        (context as HomeApi).hideProgressBar()
    }

    private fun renderError(message: String?) {
        (context as HomeApi).apply {
            showError(message ?: context.getString(R.string.default_error_message))
            hideProgressBar()
        }
    }

    private fun setArtistPhoto(link: String) {
        rootView.photo?.loadImage(url = link, transformType = ImageTransformType.CircleCrop)
    }

    private fun setArtistName(name: String) {
        rootView.name?.text = name
    }

    private fun setArtistTags(tags: List<String>) {
        tags.forEach { tag ->
            rootView.tagsContainer?.addView(getTagChip(tag))
        }
    }

    private fun getTagChip(tag: String) = Chip(context).apply {
        text = tag
        setChipBackgroundColorResource(R.color.red_500_alpha_100)
        setTextColor(ContextCompat.getColor(context, android.R.color.white))

        context?.let {
            textSize = 16f
            typeface = ResourcesCompat.getFont(context, R.font.firasans_regular)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(
                    context.dpToPix(4).toInt(),
                    0,
                    context.dpToPix(4).toInt(),
                    0
                )
            }
        }
    }

    private fun setHearersCount(count: String) {
        rootView.hearersCount?.text =
            context.resources?.getString(R.string.listeners, count.formatCount())
    }

    private fun setPlayCount(count: String) {
        rootView.playCount?.text =
            context.resources?.getString(R.string.play_count, count.formatCount())
    }

    private fun setBio(bio: String) {
        rootView.bio?.text = bio
    }

    private fun setBioPublished(date: String) {
        rootView.published?.text = context.resources?.getString(R.string.published, date)
    }

}