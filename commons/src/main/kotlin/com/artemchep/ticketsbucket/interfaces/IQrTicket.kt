package com.artemchep.ticketsbucket.interfaces

/**
 * @author Artem Chepurnoy
 */
interface IQrTicket : ISerialize {

    private companion object {
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_DEPARTURE_DATETIME = "departure_datetime"
        private const val KEY_ARRIVAL_DATETIME = "arrival_datetime"
    }

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

    override fun fromMap(map: Map<String, Any>) {
    }

    override fun toMap(): Map<String, Any> = hashMapOf(
            KEY_FIRST_NAME to firstName,
            KEY_LAST_NAME to lastName,
            KEY_DEPARTURE_DATETIME to departureDateTime,
            KEY_ARRIVAL_DATETIME to arrivalDateTime
    )

}