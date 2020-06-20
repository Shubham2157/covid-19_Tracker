package com.shubham.covid_19track.India

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shubham.covid_19track.R

class StatesAdapter(private val stateName: Array<String>, private val mContext: Context) : RecyclerView.Adapter<StatesAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.state_list, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val name = stateName[position]
        holder.txt_name.text = name
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DistrictWise::class.java)
            intent.putExtra("stateName", name)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return stateName.size
    }

    inner class myViewHolder(itemView: View) : ViewHolder(itemView) {
        var txt_name: TextView

        init {
            txt_name = itemView.findViewById(R.id.txt_name)
        }
    }

}