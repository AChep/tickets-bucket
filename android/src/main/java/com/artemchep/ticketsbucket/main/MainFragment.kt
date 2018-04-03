package com.artemchep.ticketsbucket.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.interfaces.IQrTicket
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_passengers.*

/**
 * @author Artem Chepurnoy
 */
class MainFragment : Fragment(), IMainView,
        View.OnClickListener,
        Toolbar.OnMenuItemClickListener {

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
        recyclerView.adapter = TicketsAdapter(tickets)
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

}