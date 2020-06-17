package com.shubham.covid_19track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HelpAdaptor (private val helpData: Array<HelpData>) : RecyclerView.Adapter<HelpAdaptor.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
       val  inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.help_no_list, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return helpData.size - 1
    }

    override fun onBindViewHolder(holder: myViewHolder, i: Int) {

        var i = i
        i++
        val state = helpData[i].getLoc()
        val mob = helpData[i].getNumber()

        holder.txt_ph.text = mob
        holder.txt_state.text = state

    }


    class myViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_ph: TextView
        var txt_state: TextView


        init {
            txt_ph = itemView.findViewById(R.id.txt_mob_no)
            txt_state = itemView.findViewById(R.id.txt_state_name)
        }

    }

}