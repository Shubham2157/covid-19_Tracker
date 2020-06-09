package com.shubham.covid_19track.India

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shubham.covid_19track.R

class DistrictZoneAdapter (private val zoneData: Array<ZoneData>) : RecyclerView.Adapter<DistrictZoneAdapter.myViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.districtzonelist, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return zoneData.size - 1
    }

    override fun onBindViewHolder(holder: myViewHolder, i: Int) {
        var i = i
        i++
        val district = zoneData[i].districtName
        val zone = zoneData[i].getZone()
        if (zone == "Green")
        {
           holder.relative_zone.setBackgroundColor(Color.parseColor("#008000"))
        }
        if (zone == "Red")
        {
            holder.relative_zone.setBackgroundColor(Color.parseColor("#FF0000"))
        }
        if (zone == "Orange")
        {
           holder.relative_zone.setBackgroundColor(Color.parseColor("#FFA500"))
        }

        val state = zoneData[i].stateName
        holder.txt_district.text = district
        holder.txt_zone.text = zone
        holder.txt_state.text = state
    }

    class myViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt_district: TextView
        var txt_zone: TextView
        var txt_state: TextView
        var relative_zone: RelativeLayout

        init {
            txt_district = itemView.findViewById(R.id.txt_district_name)
            txt_zone = itemView.findViewById(R.id.txt_district_zone)
            txt_state = itemView.findViewById(R.id.txt_state_name)
            relative_zone = itemView.findViewById(R.id.relative_zone)
        }

    }

}