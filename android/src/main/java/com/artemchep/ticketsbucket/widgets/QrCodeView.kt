package com.artemchep.ticketsbucket.widgets

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import com.artemchep.ticketsbucket.R
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.util.*


/**
 * @author Artem Chepurnoy
 */
class QrCodeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {

        private val sWeakHashMap = WeakHashMap<String, Bitmap>()

        private fun loadQrBitmap(contents: String, size: Int): Bitmap {
            return sWeakHashMap[contents]?.takeIf { it.width >= size }
                    ?: BarcodeEncoder()
                            .encodeBitmap(contents, BarcodeFormat.QR_CODE, size, size)
                            .also {
                                sWeakHashMap[contents] = it
                            }
        }
    }

    private val jobs = ArrayList<Job>()

    private var contentsCurrent: String? = null

    var contents: String? = null
        set(value) {
            field = value
            loadQrAsync(value)
        }

    init {
        View.inflate(context, R.layout.view_qr_code, this)
    }

    private val progressView: View = findViewById(R.id.progressView)
    private val imageView: ImageView = findViewById(R.id.imageView)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (contents != contentsCurrent) {
            // Resume loading the QR code
            loadQrAsync(contents)
        }
    }

    override fun onDetachedFromWindow() {
        cancelLoading()
        super.onDetachedFromWindow()
    }

    /** Cancels running jobs, if any. */
    private fun cancelLoading() {
        // Iterate over existing jobs and cancel all
        // of them.
        jobs.apply {
            forEach { it.cancel() }
            clear()
        }
        // View is in an undefined state now!
    }

    /** Starts a job of loading the QR-code of `contents`. */
    private fun loadQrAsync(contents: String?) {
        if (contents == null) {
            contentsCurrent = null

            // Update view
            progressView.isVisible = false
            imageView.isVisible = true
            imageView.setImageBitmap(null)
            return
        }

        cancelLoading()

        progressView.isVisible = true
        imageView.isVisible = false

        jobs += launch(UI) {
            val bitmap = async { loadQrBitmap(contents, 512) }.await()

            contentsCurrent = contents

            // Update view
            progressView.isVisible = false
            imageView.isVisible = true
            imageView.setImageBitmap(bitmap)
        }
    }

}