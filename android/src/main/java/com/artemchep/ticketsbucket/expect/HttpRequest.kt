package com.artemchep.ticketsbucket.expect

import android.util.Log
import com.github.kittinunf.fuel.httpPost

/**
 * @author Artem Chepurnoy
 */
class HttpRequest : IHttpPost {

    override fun post(url: String, params: List<Pair<String, String>>): String? {
        val (request, response, result) = url
                .httpPost()
                .body("routes%5B0%5D%5Bfrom%5D=2204001&routes%5B0%5D%5Bto%5D=2200001&routes%5B0%5D%5Bdate%5D=2018-05-17&routes%5B0%5D%5Btrain%5D=719%D0%A8".toByteArray())
                .responseString()
        val (content, error) = result
        Log.e("ERR", request.toString())
        Log.e("ERR", response.toString())
        Log.e("ERR", error.toString())
        return content
    }

}