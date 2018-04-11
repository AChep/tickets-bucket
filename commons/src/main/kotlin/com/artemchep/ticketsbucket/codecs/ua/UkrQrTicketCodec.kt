package com.artemchep.ticketsbucket.codecs.ua

import com.artemchep.ticketsbucket.data.IQrTicket
import com.artemchep.ticketsbucket.interfaces.IQrTicketCodec

/**
 * @author Artem Chepurnoy
 */
class UkrQrTicketCodec : IQrTicketCodec<IQrTicket> {

    companion object {

        /**
         * The content has to be exactly
         * 18 lines long.
         */
        private const val REQUIRED_SIZE = 20

        /**
         * Src has to contain these lines:
         * first, seconds, etc.
         */
        private val REQUIRED_FIELDS = arrayOf(
                1, // train display name
                2, // departure station code
                3, // arrival station code
                4, // departure datetime (does not include year)
                5, // arrival datetime (does not include year)
                6, // carriage details
                7, // place details
                9, // price
                12, // uid
                15, // order id
                17 // hash code
        )

        private val STATION_REGEX = Regex("""\([0-9]+\)""")

    }

    override fun encode(ticket: IQrTicket): String  = "sdfsdf"

    override fun decode(content: String, ticket: IQrTicket): Boolean {
        val lines = content.split('\n')

        // Check the structure
        if (!hasRequiredFields(lines)) {
            return false
        }

        val trans = arrayOf(
                Err(4, { ticket.departureDateTime = it }, { parseDate(it) }),
                Err(5, { ticket.arrivalDateTime = it }, { parseDate(it) }),
                Err(9, {
                    ticket.firstName = it.first
                    ticket.lastName = it.second
                }, { parsePair(it) })
        )

        trans.forEach {
            val v = it.transform(lines[it.index]) ?: return false
            it.set2(v)
        }

        return true
    }

    /**
     * @return `true` if lines of the ticket match structure
     * requirements, `false` otherwise.
     */
    private fun hasRequiredFields(lines: List<String>): Boolean {
        return lines.size == REQUIRED_SIZE && REQUIRED_FIELDS.firstOrNull {
            lines[it].isBlank()
        } == null
    }

    private fun parseDate(src: String): Long? {
        //  Date format
        // `dd.MM HH:mm`
        if (src.length >= 11) {
            // dd : 31 -> 11111
            // MM : 12 -> 1100
            // HH : 23 -> 10111
            // mm : 60 -> 111100
            // Total 20 bits needed to store this information
            // in integer
            try {
                val days = src.substring(0..1).toLong()
                val months = src.substring(3..4).toLong()
                val hours = src.substring(6..7).toLong()
                val mins = src.substring(9..10).toLong()

                var offset = 0
                var result = 0L
                // Minutes
                result = result.or(mins)
                offset += 6
                // Hours
                result = result.or(hours.shl(offset))
                offset += 5
                // Days
                result = result.or(days.shl(offset))
                offset += 5
                // Months
                result = result.or(months.shl(offset))
                offset += 4
                return result
            } catch (_: NumberFormatException) {
            }
        }
        return null
    }

    private fun parseStation(src: String): String? {
        return STATION_REGEX.find(src)?.value?.trim('(', ')')
    }

    private fun parsePair(src: String): Pair<String, String>? {
        val i = src.indexOf(' ')
        if (i == 0 || i == src.length - 1) { // not first or last character
            return null
        }

        return Pair(
                src.substring(0 until i),
                src.substring(i + 1)
        )
    }

    /**
     * @author Artem Chepurnoy
     */
    private class Err<T>(
            val index: Int,
            val setter: (T) -> Unit,
            val transform: (String) -> T?
    ) {
        fun set2(v: Any) = setter(v as T)
    }

}