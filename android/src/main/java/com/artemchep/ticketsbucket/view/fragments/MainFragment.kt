package com.artemchep.ticketsbucket.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.contracts.IMainPresenter
import com.artemchep.ticketsbucket.contracts.IMainView
import com.artemchep.ticketsbucket.data.IQrTicket
import com.artemchep.ticketsbucket.data.QrTicket
import com.artemchep.ticketsbucket.activities.TicketDetailsQrActivity
import com.artemchep.ticketsbucket.view.adapters.TicketsAdapter
import com.artemchep.ticketsbucket.view.fragments.base.FragmentBase
import com.artemchep.ticketsbucket.view.interfaces.OnItemClickListener
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_passengers.*

/**
 * @author Artem Chepurnoy
 */
class MainFragment : FragmentBase<IMainPresenter>(), IMainView,
        View.OnClickListener,
        OnItemClickListener<IQrTicket>,
        Toolbar.OnMenuItemClickListener {

    companion object {
        private const val GE = 1
    }

    private val tickets = ArrayList<IQrTicket>()

    override lateinit var presenter: IMainPresenter

    private lateinit var fabView: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_passengers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            inflateMenu(R.menu.fragment_dashboard)
            setOnMenuItemClickListener(this@MainFragment)
        }

        fabView = view.findViewById(R.id.fab)
        fabView.setOnClickListener(this)

        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.adapter = TicketsAdapter(tickets).apply {
            onItemClickListener = this@MainFragment
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            view!!.post {
                presenter.scanTicket(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> presenter.scanTicket()
        }
    }

    override fun onItemClick(view: View, data: IQrTicket) {
        when (view.id) {
            R.id.actionQrBtn -> presenter.navigateToTicketDetailsQr(data)
            R.id.actionMoreBtn -> {
                val items = listOf(
                        Pair(GE, "Move to archive")
                )

                PopupMenu(context!!, view).apply {
                    // Add options of a ticket
                    items.forEach {
                        menu.add(0, it.first, 0, it.second)
                    }

                    setOnMenuItemClickListener {
                        performTicketMoreClick(data, it.itemId)
                        return@setOnMenuItemClickListener true
                    }
                }.show()
            }
        }
    }

    private fun performTicketMoreClick(ticket: IQrTicket, action: Int) {
        when (action) {
            GE -> presenter.archiveTicket(ticket)
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> presenter.navigateToAbout()
            else -> return false
        }
        return true
    }

    override fun showTickets(tickets: List<IQrTicket>) {
        this.tickets.apply {
            clear()
            addAll(tickets)
        }

        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun showTicketsScanner() {
        IntentIntegrator
                .forSupportFragment(this)
                .setBarcodeImageEnabled(false)
                .setOrientationLocked(false)
                .setDesiredBarcodeFormats(BarcodeFormat.QR_CODE.name)
                .initiateScan()
    }

    override fun showTicketScanError() {
        MaterialDialog.Builder(context!!)
                .title("Failed to scan a ticket")
                .show()
    }

    override fun showTicketDetailsQr(ticket: IQrTicket) {
        val intent = TicketDetailsQrActivity.newIntent(context!!, ticket as QrTicket)
        startActivity(intent)
    }

    override fun showAbout() {
    }

}