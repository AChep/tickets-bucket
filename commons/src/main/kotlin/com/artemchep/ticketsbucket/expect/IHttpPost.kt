package com.artemchep.ticketsbucket.expect

/**
 * @author Artem Chepurnoy
 */
interface IHttpPost {

    fun post(url : String, params : List<Pair<String, String>>) : String?

}