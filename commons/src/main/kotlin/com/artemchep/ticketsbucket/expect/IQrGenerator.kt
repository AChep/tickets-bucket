package com.artemchep.ticketsbucket.expect

/**
 * @author Artem Chepurnoy
 */
interface IQrGenerator {

    fun makeFor(contents: String, size: Int): IFigure

}