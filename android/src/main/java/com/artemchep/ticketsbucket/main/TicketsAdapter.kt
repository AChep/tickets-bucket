package com.artemchep.ticketsbucket.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.interfaces.IQrTicket

/**
 * @author Artem Chepurnoy
 */
class TicketsAdapter(val tickets: MutableList<IQrTicket>) : RecyclerView.Adapter<TicketsAdapter.Holder>() {

    /**
     * @author Artem Chepurnoy
     */
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun getItemCount(): Int = 15

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_ticket, parent, false)
        return TicketsAdapter.Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
    }

}