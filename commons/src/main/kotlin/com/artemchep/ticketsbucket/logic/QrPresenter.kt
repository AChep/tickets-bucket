package com.artemchep.ticketsbucket2.logic

import com.artemchep.CrossPlatform
import com.artemchep.ticketsbucket2.contracts.IQrPresenter
import com.artemchep.ticketsbucket2.contracts.IQrViewApi
import com.artemchep.ticketsbucket2.expect.IFigure
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * @author Artem Chepurnoy
 */
class QrPresenter(private val contents: String) : IQrPresenter {

    override var view: IQrViewApi? = null

    private var drawable: IFigure? = null

    private var job: Job? = null

    override fun onStart() {
        view!!.apply {
            showQrCode(drawable)
        }

        if (drawable == null) {
            view!!.setLoadingIndicatorShown(true)

            // Start a job of generating QR-code for this
            // ticket.
            job?.cancel()
            if (job == null) {
                job = loadQrAsyncAndShow(contents)
            }
        } else view!!.setLoadingIndicatorShown(false)
    }

    /** Starts a job of loading the QR-code of `contents`. */
    private fun loadQrAsyncAndShow(contents: String): Job {
        return launch(CrossPlatform.coroutineContextUi) {
            drawable = async {
                // Generate the image of QR-code
                val generator = CrossPlatform.createQrGenerator()
                return@async generator.makeFor(contents, 500)
            }.await()
            view?.apply {
                setLoadingIndicatorShown(false)
                showQrCode(drawable)
            }
        }
    }

}