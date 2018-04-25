package com.artemchep.ticketsbucket2.data

/**
 * @author Artem Chepurnoy
 */
interface IQrTicket {

    var key: String

    var name: String

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