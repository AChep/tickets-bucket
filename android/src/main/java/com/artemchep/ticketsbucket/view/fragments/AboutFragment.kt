package com.artemchep.ticketsbucket.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket2.contracts.IAboutPresenter
import com.artemchep.ticketsbucket2.contracts.IAboutView
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrView
import com.artemchep.ticketsbucket.view.fragments.base.FragmentBase
import kotlinx.android.synthetic.main.fragment_ticket_details_qr.*

/**
 * @author Artem Chepurnoy
 */
class AboutFragment : FragmentBase<IAboutPresenter>(), IAboutView {

    override lateinit var presenter: IAboutPresenter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}