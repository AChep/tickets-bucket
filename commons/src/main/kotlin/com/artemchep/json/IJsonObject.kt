package com.artemchep.json

/**
 * @author Artem Chepurnoy
 */
interface IJsonObject {

    fun int(field: String): Int?

    fun double(field: String): Double?

    fun string(field: String): String?

    fun obj(field: String): IJsonObject?

    fun array(field: String): IJsonArray<IJsonObject>?

}
