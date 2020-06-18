package com.shubham.covid_19track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.help_no_list.view.*

class HelpAdaptor (private val helpData: Array<HelpData>) : RecyclerView.Adapter<HelpAdaptor.myViewHolder>() {


    var onClickListener:(String)->Unit={}

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

        holder.bind(state,mob,onClickListener)

    }


    class myViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(state:String,number:String,onclick:(String)->Unit){
            itemView.txt_mob_no.text = number
            itemView.txt_state_name.text = state
            itemView.call_btn.setOnClickListener {
                val first = number.split(",").first()
                onclick(first)
            }
        }

    }

}