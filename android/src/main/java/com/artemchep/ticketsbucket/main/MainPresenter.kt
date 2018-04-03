package com.artemchep.ticketsbucket.main

import android.util.Log
import com.artemchep.ticketsbucket.codecs.ua.UkrQrTicketCodec
import com.artemchep.ticketsbucket.interfaces.IQrTicket
import com.artemchep.ticketsbucket.models.QrTicket
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

        val tickets = value.documents.map {
            object : IQrTicket {
                override var firstName: String
                    get() = "Artem"
                    set(value) {}
                override var lastName: String
                    get() = "Chepurnoy"
                    set(value) {}
                override var departureDateTime: Long
                    get() = 1600L
                    set(value) {}
                override var arrivalDateTime: Long
                    get() = 1800L
                    set(value) {}
            }
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
        val map = mapOf(
                Pair("firstName", "Artem"),
                Pair("lastName", "Chepurnoy")
        )

        collection.document().set(map)
    }

}