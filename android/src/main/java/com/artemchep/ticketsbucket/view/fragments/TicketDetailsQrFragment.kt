package com.artemchep.ticketsbucket.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket.contracts.ITicketDetailsQrView
import com.artemchep.ticketsbucket.view.fragments.base.FragmentBase
import kotlinx.android.synthetic.main.fragment_ticket_details_qr.*

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrFragment : FragmentBase<ITicketDetailsQrPresenter>(), ITicketDetailsQrView {

    override lateinit var presenter: ITicketDetailsQrPresenter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ticket_details_qr, container, false)
    }

    override fun showQrCode(contents: String) {
        qrCodeView.contents = contents
    }

    override fun showCode(code: String) {
        codeTextView.text = code
    }

}