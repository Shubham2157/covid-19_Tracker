package com.shubham.covid_19track.India

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shubham.covid_19track.R

class DistrictAdapter(var districts: Array<String?>, var cases: Array<String?>) : RecyclerView.Adapter<DistrictAdapter.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.district_list, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val districtName = districts[position]
        val confirmedCases = cases[position]
        holder.txt_district_name.text = districtName
        holder.txt_district_count.text = confirmedCases
    }

    override fun getItemCount(): Int {
        return districts.size
    }

    inner class myViewHolder(itemView: View) : ViewHolder(itemView) {
        var txt_district_name: TextView
        var txt_district_count: TextView

        init {
            txt_district_name = itemView.findViewById(R.id.txt_district_name)
            txt_district_count = itemView.findViewById(R.id.txt_district_count)
        }
    }

}