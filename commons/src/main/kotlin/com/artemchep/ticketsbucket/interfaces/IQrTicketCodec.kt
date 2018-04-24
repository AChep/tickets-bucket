package com.artemchep.ticketsbucket2.interfaces

import com.artemchep.ticketsbucket2.data.IQrTicket

/**
 * @author Artem Chepurnoy
 */
interface IQrTicketCodec<in T : IQrTicket> {

    fun encode(ticket: T) : String

    fun decode(content: String, ticket: T): Boolean

}