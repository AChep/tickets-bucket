package com.artemchep.ticketsbucket.data

import android.os.Parcelable
import com.artemchep.ticketsbucket2.data.IQrTicket
import kotlinx.android.parcel.Parcelize

/**
 * @author Artem Chepurnoy
 */
@Parcelize
data class QrTicket(
        override var key: String = "",
        override var name: String = "",
        override var departureDateTime: Long = 0L,
        override var arrivalDateTime: Long = 0L
) : IQrTicket, Parcelable
