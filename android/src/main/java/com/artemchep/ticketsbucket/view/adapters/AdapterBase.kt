package com.artemchep.ticketsbucket.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.artemchep.ticketsbucket.view.interfaces.OnItemClickListener

/**
 * @author Artem Chepurnoy
 */
abstract class AdapterBase<M, H : RecyclerView.ViewHolder>(
        protected val models : MutableList<M>
) : RecyclerView.Adapter<H>(), OnItemClickListener<Int> {

    var onItemClickListener: OnItemClickListener<M>? = null

    override fun getItemCount(): Int = models.size

    override fun onItemClick(view: View, data: Int) {
        onItemClickListener?.onItemClick(view, models[data])
    }

    /**
     * @author Artem Chepurnoy
     */
    open class ViewHolderBase(
            private val listener: OnItemClickListener<Int>,
            itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position >= 0) {
                listener.onItemClick(view, position)
            }
        }

    }

}