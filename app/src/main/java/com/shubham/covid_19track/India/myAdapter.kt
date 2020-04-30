package com.shubham.covid_19track.India

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.shubham.covid_19track.R

class myAdapter(private val stateData: Array<StateData>) :
    RecyclerView.Adapter<myAdapter.myViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): myViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_state, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: myViewHolder,
        i: Int
    ) {
        var i = i
        i++
        val stateName = stateData[i].stateName
        val confirmed = stateData[i].getConfirmed()
        val active = stateData[i].getActive()
        val recovered = stateData[i].getRecovered()
        val deaths = stateData[i].getDeaths()
        val lastupdatedtime = stateData[i].getLastupdatedtime()
        holder.txt_confirmed_st.text = confirmed
        holder.txt_active_st.text = active
        holder.txt_recovered_st.text = recovered
        holder.txt_deaths_st.text = deaths
        holder.txt_state.text = stateName
        holder.txt_updated_stt.text = lastupdatedtime
    }

    override fun getItemCount(): Int {
        return stateData.size - 1
    }

    inner class myViewHolder(itemView: View) : ViewHolder(itemView) {
        var txt_confirmed_st: TextView
        var txt_active_st: TextView
        var txt_recovered_st: TextView
        var txt_deaths_st: TextView
        var txt_state: TextView
        var txt_updated_stt: TextView

        init {
            txt_confirmed_st = itemView.findViewById(R.id.txt_confirmed_st)
            txt_active_st = itemView.findViewById(R.id.txt_active_st)
            txt_recovered_st = itemView.findViewById(R.id.txt_recovered_st)
            txt_deaths_st = itemView.findViewById(R.id.txt_deaths_st)
            txt_state = itemView.findViewById(R.id.txt_state)
            txt_updated_stt = itemView.findViewById(R.id.txt_updated_stt)
        }
    }

}