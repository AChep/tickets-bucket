package com.artemchep.ticketsbucket.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.data.Joint
import com.artemchep.ticketsbucket.view.adapters.JointAdapter
import com.artemchep.ticketsbucket.view.fragments.base.FragmentBase
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsView
import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.ticketsbucket2.expect.IFigure
import kotlinx.android.synthetic.main.fragment_ticket_details.*

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsFragment : FragmentBase<ITicketDetailsPresenter>(), ITicketDetailsView {

    override lateinit var presenter: ITicketDetailsPresenter

    private val joints: MutableList<Joint> = ArrayList()

    private val jointAdapter = JointAdapter(joints)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ticket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        routeRecycleView.adapter = jointAdapter
        /*
        qrImageView = view.findViewById(R.id.qrCodeView)
        qrProgressBar = view.findViewById(R.id.qrCodeProgressBar)
        codeTextView = view.findViewById(R.id.codeTextView)
        routeTextView = view.findViewById(R.id.routeTextView)
        */
    }

    override fun setLoadingIndicatorShown(isShown: Boolean) {
    }

    override fun showQrCode(figure: IFigure?) {
    }

    override fun showTicket(ticket: IQrTicket) {
    }

    override fun setRouteLoadingIndicatorShown(isShown: Boolean) {
        loader.isVisible = isShown
    }

    override fun showNoRoute() {
        warning.isVisible = true
    }

    override fun showRoute(joints: List<Joint>) {
        this.joints.apply {
            clear()
            addAll(joints)
        }

        warning.isVisible = false
        jointAdapter.notifyDataSetChanged()
    }

}