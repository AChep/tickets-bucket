package com.artemchep.ticketsbucket.main

import android.util.Log
import com.artemchep.ticketsbucket.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket.contracts.IMainPresenter
import com.artemchep.ticketsbucket.contracts.IMainView
import com.artemchep.ticketsbucket.data.IQrTicket
import com.google.firebase.firestore.*


/**
 * @author Artem Chepurnoy
 */
class MainPresenter : IMainPresenter {

    companion object {
        private const val TAG = "MainPresenter"
    }

    private val registrations: MutableList<ListenerRegistration> = ArrayList()

    private val collection: CollectionReference by lazy {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("tickets")
    }

    private val listener = EventListener<QuerySnapshot> { value, e ->
        if (e != null) {
            Log.d(TAG, "Listener error", e)
            return@EventListener
        }

        val tickets = value!!.documents.map {
            QrTicket(
                    firstName = "Artem Chepurnoy"
            )
        }
        processTickets(tickets)
    }

    override var view: IMainView? = null

    override fun onStart() {
        val reg = collection.addSnapshotListener(listener)
        registrations += reg
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
        registrations.forEach { it.remove() }
        registrations.clear()
    }

    private fun processTickets(tickets: List<IQrTicket>) {
        view!!.showTickets(tickets)
    }

    override fun scanTicket() {
        view!!.showTicketsScanner()
    }

    override fun scanTicket(contents: String) {
        val ticket = QrTicket()
        val codec = UkrQrTicketCodec()
        val succeed = codec.decode(contents, ticket)

        if (!succeed) {
            view?.showTicketScanError()
            return
        }

    }

    override fun archiveTicket(ticket: IQrTicket) {
    }

    override fun navigateToTicketDetailsQr(ticket: IQrTicket) {
        view!!.showTicketDetailsQr(ticket)
    }

    override fun navigateToAbout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}