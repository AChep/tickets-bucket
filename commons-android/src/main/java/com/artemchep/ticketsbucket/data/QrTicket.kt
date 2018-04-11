package com.artemchep.ticketsbucket.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Artem Chepurnoy
 */
@Parcelize
data class QrTicket(
        override var firstName: String = "",
        override var lastName: String = "",
        override var departureDateTime: Long = 0L,
        override var arrivalDateTime: Long = 0L
) : IQrTicket, Parcelable
