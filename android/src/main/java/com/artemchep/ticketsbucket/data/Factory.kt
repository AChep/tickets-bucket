package com.artemchep.ticketsbucket.data

/**
 * @author Artem Chepurnoy
 */
class FactoryAndroid : Factory {

    override fun createQrTicket(): IQrTicket = QrTicket()

}