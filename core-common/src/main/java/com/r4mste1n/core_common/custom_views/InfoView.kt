package com.r4mste1n.core_common.custom_views

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.withTranslation
import com.r4mste1n.core_common.R
import kotlin.math.ceil
import kotlin.math.roundToInt

/**
 * Created by Alex Shtain on 26.07.2020.
 */

class InfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var mainText = ""
        set(value) {
            field = value

            invalidate()
        }
    var headerText = ""
        set(value) {
            field = value

            invalidate()
        }

    private var padding = DEFAULT_PADDING
    private var iconHeight = DEFAULT_ICON_HEIGHT
    private var iconPadding = DEFAULT_ICON_PADDING
    private var textSize = DEFAULT_TEXT_SIZE

    private var bgColor = ContextCompat.getColor(context, DEFAULT_BG_COLOR)
    private var textColor = ContextCompat.getColor(context, DEFAULT_TEXT_COLOR)

    private var headerFont = ResourcesCompat.getFont(context, R.font.firasans_bold)
    private var mainFont = ResourcesCompat.getFont(context, R.font.firasans_bold)

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels

    private val bgPaint = Paint().apply {
        style = Paint.Style.FILL
        color = bgColor
        isAntiAlias = true
    }

    private val mainTextPaint = TextPaint().apply {
        isAntiAlias = true
    }

    private val headerTextPaint = TextPaint().apply {
        isAntiAlias = true
    }

    private var icon = ContextCompat.getDrawable(context, R.drawable.ic_listening)?.apply {
        this.setBounds(0, 0, iconHeight, iconHeight)
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InfoView,
            defStyleAttr,
            defStyleRes
        ).let {
            headerText = it.getString(R.styleable.InfoView_header_text).orEmpty()
            mainText = it.getString(R.styleable.InfoView_main_text).orEmpty()
            textSize = it.getDimension(R.styleable.InfoView_text_size, DEFAULT_TEXT_SIZE)

            padding = it.getDimension(R.styleable.InfoView_padding, DEFAULT_PADDING)
            iconHeight = it.getDimension(
                R.styleable.InfoView_icon_height,
                DEFAULT_ICON_HEIGHT.toFloat()
            ).roundToInt()
            iconPadding = it.getDimension(
                R.styleable.InfoView_icon_padding,
                DEFAULT_ICON_PADDING.toFloat()
            ).roundToInt()

            bgColor = it.getColor(
                R.styleable.InfoView_bg_color,
                ContextCompat.getColor(context, DEFAULT_BG_COLOR)
            )
            textColor = it.getColor(
                R.styleable.InfoView_text_color,
                ContextCompat.getColor(context, DEFAULT_TEXT_COLOR)
            )

            try {
                headerFont = ResourcesCompat.getFont(
                    context,
                    it.getResourceId(R.styleable.InfoView_header_font, R.font.firasans_bold)
                )
                mainFont = ResourcesCompat.getFont(
                    context,
                    it.getResourceId(R.styleable.InfoView_main_font, R.font.firasans_bold)
                )
                icon = it.getDrawable(R.styleable.InfoView_icon)?.apply {
                    this.setBounds(0, 0, iconHeight, iconHeight)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            it.recycle()
        }

        setupFields()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (heightMode) {
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> {
                setMeasuredDimension(widthSize, (padding * 2 + iconHeight).toInt())
            }
            MeasureSpec.EXACTLY -> {
                iconHeight = (heightSize * 0.6).roundToInt()
                padding = (heightSize * 0.2).toFloat()
                textSize = (iconHeight * 0.2).toFloat()

                setupDrawableHeight()
                setupTextSize()

                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        }

    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawRect(
            0f,
            0f,
            screenWidth.toFloat(),
            padding * 2 + iconHeight,
            bgPaint
        )

        canvas.withTranslation(padding, padding) {
            icon?.draw(canvas)
        }

        canvas.drawText(
            headerText,
            padding + iconHeight + iconPadding,
            getTextHeight(headerTextPaint) + padding,
            headerTextPaint
        )

        canvas.drawText(
            mainText,
            padding + iconHeight + iconPadding,
            padding + iconHeight,
            mainTextPaint
        )

    }

    private fun setupFields() {
        setupDrawableHeight()
        setupTextSize()
        bgPaint.color = bgColor
        headerTextPaint.apply {
            color = textColor
            typeface = headerFont
        }
        mainTextPaint.apply {
            color = textColor
            typeface = mainFont
        }
    }

    private fun setupTextSize() {
        mainTextPaint.textSize = textSize
        headerTextPaint.textSize = textSize
    }

    private fun setupDrawableHeight() {
        icon?.apply {
            this.setBounds(0, 0, iconHeight, iconHeight)
        }
    }

    private fun getTextHeight(paint: Paint): Float {
        val metric: Paint.FontMetrics = paint.fontMetrics
        val textHeight = ceil(metric.descent - metric.ascent)

        return textHeight - metric.descent
    }

    companion object {

        private const val DEFAULT_PADDING = 42f
        private const val DEFAULT_ICON_HEIGHT = 64
        private const val DEFAULT_ICON_PADDING = 32
        private const val DEFAULT_TEXT_SIZE = 26f

        private const val DEFAULT_TEXT_COLOR = android.R.color.white
        private const val DEFAULT_BG_COLOR = android.R.color.holo_red_dark

    }

}