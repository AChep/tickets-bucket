package com.artemchep.json

/**
 * @author Artem Chepurnoy
 */
interface IJsonArray<T> {

    fun at(i: Int): T

    /**
     * @return the size of this JSON array
     */
    fun size(): Int

}