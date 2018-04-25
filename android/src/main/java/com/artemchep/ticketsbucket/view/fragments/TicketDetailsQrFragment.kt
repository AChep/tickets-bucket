package com.artemchep.ticketsbucket.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.expect.unbox
import com.artemchep.ticketsbucket.view.fragments.base.FragmentBase
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrPresenter
import com.artemchep.ticketsbucket2.contracts.ITicketDetailsQrView
import com.artemchep.ticketsbucket2.expect.IFigure

/**
 * @author Artem Chepurnoy
 */
class TicketDetailsQrFragment : FragmentBase<ITicketDetailsQrPresenter>(), ITicketDetailsQrView {

    override lateinit var presenter: ITicketDetailsQrPresenter

    private lateinit var qrImageView: ImageView
    private lateinit var qrProgressBar: ProgressBar
    private lateinit var codeTextView: TextView
    private lateinit var routeTextView: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ticket_details_qr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrImageView = view.findViewById(R.id.qrCodeView)
        qrProgressBar = view.findViewById(R.id.qrCodeProgressBar)
        codeTextView = view.findViewById(R.id.codeTextView)
        routeTextView = view.findViewById(R.id.routeTextView)
    }

    override fun setLoadingIndicatorShown(isShown: Boolean) {
        qrProgressBar.isVisible = isShown
    }

    override fun showQrCode(figure: IFigure?) {
        qrImageView.setImageBitmap(figure?.unbox()?.bitmap)
    }

    override fun showCode(code: String) {
        codeTextView.text = code
    }

    override fun showRoute(route: String) {
        routeTextView.text = route
    }

}