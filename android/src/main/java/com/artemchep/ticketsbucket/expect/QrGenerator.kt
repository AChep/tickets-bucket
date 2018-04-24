package com.artemchep.ticketsbucket.expect

import android.graphics.Bitmap
import com.artemchep.ticketsbucket2.expect.IFigure
import com.artemchep.ticketsbucket2.expect.IQrGenerator
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
                ?: Bitmap
                        .createBitmap(size, size, Bitmap.Config.ARGB_8888)
                        .also {
                            sCacheMap[contents] = it
                        }

        Thread.sleep(2000)

        return Figure(bitmap)
    }

}