package com.artemchep.ticketsbucket.interfaces

/**
 * @author Artem Chepurnoy
 */
interface IQrTicketCodec<in T : IQrTicket> {

    fun encode(ticket: T) : String

    fun decode(content: String, ticket: T): Boolean

}