package com.artemchep.ticketsbucket

import com.artemchep.ticketsbucket.data.IQrTicket
import com.artemchep.ticketsbucket.data.QrTicket

/**
 * @author Artem Chepurnoy
 */
class FactoryAndroid : Factory {

    override fun createQrTicket(): IQrTicket = QrTicket()

}