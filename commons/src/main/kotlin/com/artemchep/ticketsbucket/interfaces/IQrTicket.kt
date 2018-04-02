package com.artemchep.ticketsbucket.interfaces

/**
 * @author Artem Chepurnoy
 */
interface IQrTicket {
    var firstName: String
    var lastName: String

    /**
     * Date time of train
     * departure
     */
    var departureDateTime: Long

    /**
     * Date time of train
     * arrival
     */
    var arrivalDateTime: Long
}