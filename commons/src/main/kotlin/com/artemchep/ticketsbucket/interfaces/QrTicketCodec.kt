package com.artemchep.ticketsbucket.interfaces

/**
 * @author Artem Chepurnoy
 */
interface QrTicketCodec<in T : QrTicket> {

    fun encode(ticket: T) : String

    fun decode(content: String, ticket: T): Boolean

}