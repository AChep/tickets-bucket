package com.artemchep.ticketsbucket.models

import com.artemchep.ticketsbucket.interfaces.IQrTicket

/**
 * @author Artem Chepurnoy
 */
data class QrTicket(
        override var firstName: String = "",
        override var lastName: String = "",
        override var departureDateTime: Long = 0L,
        override var arrivalDateTime: Long = 0L
) : IQrTicket
