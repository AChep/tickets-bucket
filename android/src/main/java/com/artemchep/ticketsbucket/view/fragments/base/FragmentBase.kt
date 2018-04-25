package com.artemchep.ticketsbucket.view.fragments.base

import android.support.v4.app.Fragment
import com.artemchep.mvp.IPresenter

/**
 * @author Artem Chepurnoy
 */
abstract class FragmentBase<P : IPresenter<*>> : Fragment() {

    abstract var presenter: P

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

}