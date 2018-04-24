package com.artemchep.ticketsbucket.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrView
import com.artemchep.ticketsbucket2.expect.IFigure
import com.artemchep.ticketsbucket.expect.platformDependent
import com.artemchep.ticketsbucket.view.fragments.base.FragmentBase

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrFragment : FragmentBase<ITicketDetailsQrPresenter>(), ITicketDetailsQrView {

    override lateinit var presenter: ITicketDetailsQrPresenter

    private lateinit var qrImageView: ImageView
    private lateinit var codeTextView: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ticket_details_qr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrImageView = view.findViewById(R.id.qrCodeView)
        codeTextView = view.findViewById(R.id.codeTextView)
    }

    override fun setLoadingIndicatorShown(isShown: Boolean) {
    }

    override fun showQrCode(figure: IFigure?) {
//        qrImageView.setImageBitmap(figure?.platformDependent()?.bitmap)
    }

    override fun showCode(code: String) {
        codeTextView.text = code
    }

}