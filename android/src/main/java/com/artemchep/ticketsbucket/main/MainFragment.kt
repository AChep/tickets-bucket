package com.artemchep.ticketsbucket.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
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
import com.artemchep.ticketsbucket.data.QrTicket
import com.artemchep.ticketsbucket.data.IQrTicket
import com.artemchep.ticketsbucket.ticket_details_qr.TicketDetailsQrActivity
import com.artemchep.ticketsbucket.view.adapters.TicketsAdapter
import com.artemchep.ticketsbucket.view.interfaces.OnItemClickListener
import com.artemchep.ticketsbucket.widgets.QrCodeView
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_passengers.*

/**
 * @author Artem Chepurnoy
 */
class MainFragment : Fragment(), IMainView,
        View.OnClickListener,
        Toolbar.OnMenuItemClickListener, OnItemClickListener<IQrTicket> {

    private val tickets = ArrayList<IQrTicket>()

    override lateinit var presenter: IMainPresenter

    private lateinit var fabView: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_passengers, container, false)
    }

    private lateinit var qrCodeVide: QrCodeView

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

        qrCodeVide = QrCodeView(context!!).apply {
            setBackgroundColor(Color.RED)
        }
        (view as ViewGroup).addView(qrCodeVide, ViewGroup.LayoutParams(512, 512))
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

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> presenter.scanTicket()
        }
    }

    override fun onItemClick(view: View, data: IQrTicket) {
        presenter.navigateToTicketDetailsQr(data)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
            }
            R.id.action_support -> {
            }
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

}