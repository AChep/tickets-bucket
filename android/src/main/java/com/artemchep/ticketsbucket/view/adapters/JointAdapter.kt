package com.artemchep.ticketsbucket.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.artemchep.ticketsbucket.R
import com.artemchep.ticketsbucket.data.Joint
import com.artemchep.ticketsbucket.view.interfaces.OnItemClickListener

/**
 * @author Artem Chepurnoy
 */
class JointAdapter(tickets: MutableList<Joint>) : AdapterBase<Joint, JointAdapter.Holder>(tickets) {

    /**
     * @author Artem Chepurnoy
     */
    class Holder(listener: OnItemClickListener<Int>, view: View) : AdapterBase.ViewHolderBase(listener, view) {

        internal val nameTextView = view.findViewById<TextView>(R.id.jointName)
        internal val arrivalTimeTextView = view.findViewById<TextView>(R.id.arrivalTime)
        internal val departureTimeTextView = view.findViewById<TextView>(R.id.departureTime)
        internal val statusTextView = view.findViewById<TextView>(R.id.timeTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_route, parent, false)
        return Holder(this, v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = models[position]
        holder.apply {
            nameTextView.text = model.name
            arrivalTimeTextView.text = model.arrivalTime
            departureTimeTextView.text = model.departureTime
            statusTextView.text = "${model.distance} km"
        }
    }

}