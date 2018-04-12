package com.artemchep.ticketsbucket.expect

/**
 * @author Artem Chepurnoy
 */
interface CrossQrGenerator {

    fun generate(contents: String, size: Int): CrossDrawable

}