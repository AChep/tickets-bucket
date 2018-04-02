package com.artemchep.ticketsbucket.main

import android.util.Log
import com.artemchep.ticketsbucket.interfaces.IQrTicket
import com.google.firebase.firestore.*


/**
 * @author Artem Chepurnoy
 */
class MainPresenter : MainPresenterBase {

    companion object {
        private const val TAG = "MainPresenter"
    }

    private val registrations: MutableList<ListenerRegistration> = ArrayList()

    private val collection: CollectionReference by lazy {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("")
    }

    private val listener = EventListener<QuerySnapshot> { value, e ->
        if (e != null) {
            Log.d(TAG, "Listener error", e)
            return@EventListener
        }

        val tickets = value.documents.map {
            object : IQrTicket {}
        }
        processTickets(tickets)
    }

    override var view: MainViewBase? = null

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

}