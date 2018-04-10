package com.artemchep.ticketsbucket.interfaces

/**
 * @author Artem Chepurnoy
 */
interface ISerialize {

    fun fromMap(map : Map<String, Any>)

    fun toMap() : Map<String, Any>

}