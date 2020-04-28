package com.shubham.covid_19track

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.ArrayList

class MyCustomAdapter(context: Context, private val countryModelsList: List<CountryModel>) : ArrayAdapter<CountryModel?>(context, R.layout.list_custom_item, countryModelsList) {

    private var countryModelsListFiltered: List<CountryModel>
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_custom_item, null, true)
        val tvCountryName = view.findViewById<TextView>(R.id.tvCountryName)
        val imageView = view.findViewById<ImageView>(R.id.imageFlag)
        tvCountryName.text = countryModelsListFiltered[position].country
        Glide.with(context).load(countryModelsListFiltered[position].flag).into(imageView)
        return view
    }

    override fun getCount(): Int {
        return countryModelsListFiltered.size
    }

    override fun getItem(position: Int): CountryModel? {
        return countryModelsListFiltered[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterResults = FilterResults()
                if (constraint == null || constraint.length == 0) {
                    filterResults.count = countryModelsList.size
                    filterResults.values = countryModelsList
                } else {
                    val resultsModel: MutableList<CountryModel> = ArrayList()
                    val searchStr = constraint.toString().toLowerCase()
                    for (itemsModel in countryModelsList) {
                        if (itemsModel.country!!.toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel)
                        }
                        filterResults.count = resultsModel.size
                        filterResults.values = resultsModel
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                countryModelsListFiltered = results.values as List<CountryModel>
                AffectedCountries.countryModelsList = results.values as MutableList<CountryModel>
                notifyDataSetChanged()
            }
        }
    }

    init {
        countryModelsListFiltered = countryModelsList
    }

}