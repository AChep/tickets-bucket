package com.artemchep.ticketsbucket.expect

import android.graphics.Bitmap
import com.artemchep.ticketsbucket2.expect.IFigure
import com.artemchep.ticketsbucket2.expect.IQrGenerator
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.util.*

/**
 * @author Artem Chepurnoy
 */
class QrGenerator : IQrGenerator {

    companion object {
        private val sCacheMap = WeakHashMap<String, Bitmap>()
    }

    override fun makeFor(contents: String, size: Int): IFigure {
        val bitmap = sCacheMap[contents]?.takeIf { it.width >= size }
                ?: BarcodeEncoder()
                        .encodeBitmap(contents, BarcodeFormat.QR_CODE, size, size)
                        .also {
                            sCacheMap[contents] = it
                        }

        return Figure(bitmap)
    }

}