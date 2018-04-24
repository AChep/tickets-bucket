package com.artemchep.ticketsbucket2.expect

/**
 * @author Artem Chepurnoy
 */
interface IQrGenerator {

    fun makeFor(contents: String, size: Int): IFigure

}