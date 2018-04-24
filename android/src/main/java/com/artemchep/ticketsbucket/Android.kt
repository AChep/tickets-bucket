package com.artemchep.ticketsbucket

import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.ticketsbucket.data.QrTicket
import com.artemchep.ticketsbucket2.expect.IQrGenerator
import com.artemchep.ticketsbucket.expect.QrGenerator
import com.artemchep.ticketsbucket2.CrossPlatform
import kotlinx.coroutines.experimental.android.UI

/**
 * @author Artem Chepurnoy
 */
class Android : CrossPlatform {

//    override val coroutineContextUi = UI

    override fun createQrTicket(): IQrTicket = QrTicket()

    override fun createQrGenerator(): IQrGenerator = QrGenerator()

}