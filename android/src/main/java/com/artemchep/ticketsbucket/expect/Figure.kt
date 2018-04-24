package com.artemchep.ticketsbucket.expect

import android.graphics.Bitmap
import com.artemchep.ticketsbucket2.expect.IFigure

fun IFigure.platformDependent() = this as Figure

/**
 * @author Artem Chepurnoy
 */
data class Figure(val bitmap: Bitmap) : IFigure
