package com.artemchep.ticketsbucket2.logic

import com.artemchep.ticketsbucket2.CrossPlatform
import com.artemchep.ticketsbucket2.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrView
import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.ticketsbucket2.expect.IFigure
//import kotlinx.coroutines.experimental.Job
//import kotlinx.coroutines.experimental.async
//import kotlinx.coroutines.experimental.launch

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrPresenter(private val ticket: IQrTicket) : ITicketDetailsQrPresenter {

    override var view: ITicketDetailsQrView? = null

    /**
     * Encoded contents of the e-ticket.
     */
    private val ticketContents by lazy {
        val codec = UkrQrTicketCodec()
        return@lazy codec.encode(ticket)
    }

    private var drawable: IFigure? = null

//    private var job: Job? = null

    override fun onStart() {
        view!!.apply {
            showCode("000B380B-C0EAEC2F-0001")
            showQrCode(drawable)
        }

        if (drawable != null) {
            view!!.setLoadingIndicatorShown(true)

            // Start a job of generating QR-code for this
            // ticket.
//            job?.cancel()
//            if (job == null) {
//                job = loadQrAsyncAndShow(ticketContents)
//            }
        } else view!!.setLoadingIndicatorShown(false)
    }

    /** Starts a job of loading the QR-code of `contents`. *
    private fun loadQrAsyncAndShow(contents: String): Job {
        return launch(CrossPlatform.coroutineContextUi) {
            drawable = async {
                // Generate the image of QR-code
                val generator = CrossPlatform.createQrGenerator()
                return@async generator.makeFor(contents, 500)
            }.await()
            view?.showQrCode(drawable)
        }
    }*/

}