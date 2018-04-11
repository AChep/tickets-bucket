package com.artemchep.ticketsbucket.view.interfaces

import android.view.View

/**
 * @author Artem Chepurnoy
 */
interface OnItemClickListener<T> {

    fun onItemClick(view: View, data: T)

}
