package com.artemchep.ticketsbucket.data

/**
 * @author Artem Chepurnoy
 */
data class Joint(
        val name: String?,
        val code: Int?,
        val arrivalTime: String?,
        val departureTime: String?,
        val distance: String?,
        val latitude: Double?,
        val longitude: Double?
)