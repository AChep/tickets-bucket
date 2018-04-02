package com.artemchep.ticketsbucket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_passengers.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_passengers)

        recycler.adapter = MyDapter()
    }

    class MyDapter : RecyclerView.Adapter<MyDapter.Holder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val inflater = LayoutInflater.from(parent.context)
            val v = inflater.inflate(R.layout.item_ticket, parent, false)
            return MyDapter.Holder(v)
        }

        override fun getItemCount(): Int = 15

        override fun onBindViewHolder(holder: Holder, position: Int) {
        }

        class Holder(view: View) : RecyclerView.ViewHolder(view) {

        }

    }

}