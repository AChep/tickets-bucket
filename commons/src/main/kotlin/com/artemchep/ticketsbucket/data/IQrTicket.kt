package com.artemchep.ticketsbucket.data

/**
 * @author Artem Chepurnoy
 */
interface IQrTicket {

    var key: String

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