package com.artemchep.ticketsbucket

import com.artemchep.ticketsbucket.data.IQrTicket
import com.artemchep.ticketsbucket.expect.IQrGenerator
import com.artemchep.ticketsbucket.expect.QrGenerator
import kotlinx.coroutines.experimental.android.UI

/**
 * @author Artem Chepurnoy
 */
class Android : CrossPlatform {

    override val coroutineContextUi = UI

    override fun createQrTicket(): IQrTicket = QrTicket()

    override fun createQrGenerator(): IQrGenerator = QrGenerator()

}