package com.artemchep.ticketsbucket.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket2.data.IQrTicket
import com.artemchep.ticketsbucket.view.interfaces.OnItemClickListener

/**
 * @author Artem Chepurnoy
 */
class TicketsAdapter(tickets: MutableList<IQrTicket>) : AdapterBase<IQrTicket, TicketsAdapter.Holder>(tickets) {

    /**
     * @author Artem Chepurnoy
     */
    class Holder(listener: OnItemClickListener<Int>, view: View) : AdapterBase.ViewHolderBase(listener, view) {

        init {
            view.apply {
                setOnClickListener(this@Holder)
                findViewById<View>(R.id.actionMoreBtn).setOnClickListener(this@Holder)
                findViewById<View>(R.id.actionQrBtn).setOnClickListener(this@Holder)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_ticket, parent, false)
        return Holder(this, v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
    }

}