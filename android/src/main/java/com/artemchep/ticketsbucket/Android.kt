package com.artemchep.ticketsbucket

import com.artemchep.CrossPlatform
import com.artemchep.json.IJson
import com.artemchep.json.IJsonArray
import com.artemchep.json.IJsonObject
import com.artemchep.ticketsbucket.data.QrTicket
import com.artemchep.ticketsbucket.expect.HttpRequest
import com.artemchep.ticketsbucket.expect.IHttpPost
import com.artemchep.ticketsbucket.expect.QrGenerator
import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.ticketsbucket2.expect.IQrGenerator
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import kotlinx.coroutines.experimental.android.UI
import org.json.JSONObject

/**
 * @author Artem Chepurnoy
 */
class Android : CrossPlatform {

    override val coroutineContextUi = UI

    override fun createQrTicket(): IQrTicket = QrTicket()

    override fun createQrGenerator(): IQrGenerator = QrGenerator()

    override fun createHttpPost(): IHttpPost = HttpRequest()

    override fun createJson(): IJson = AndroidJson()

}

/**
 * @author Artem Chepurnoy
 */
class AndroidJson : IJson {

    override fun from(content: String): IJsonObject? {
        val json = Parser().parse(StringBuilder(content)) as JsonObject?
        return json?.let {
            AndroidJsonObject(it)
        }
    }

}

/**
 * @author Artem Chepurnoy
 */
class AndroidJsonObject(private val json: JsonObject) : IJsonObject {

    override fun int(field: String): Int? = json.int(field)

    override fun double(field: String): Double? = json.double(field)

    override fun string(field: String): String? = json.string(field)

    override fun obj(field: String): IJsonObject? {
        return json.obj(field)?.let {
            AndroidJsonObject(it)
        }
    }

    override fun array(field: String): IJsonArray<IJsonObject>? {
        return json.array<JSONObject>(field)?.let {
            AndroidJsonArray(it)
        }
    }

}

/**
 * @author Artem Chepurnoy
 */
class AndroidJsonArray<D, T>(private val array: JsonArray<D>) : IJsonArray<T> {

    override fun at(i: Int): T {
        val obj = array[i]
        return when (obj) {
            is JsonObject -> AndroidJsonObject(obj) as T
            else -> obj as T
        }
    }

    override fun size(): Int = array.size

}
