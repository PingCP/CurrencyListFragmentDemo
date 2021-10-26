package com.example.currencylistdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylistdemo.R
import com.example.currencylistdemo.database.Currency


class RecyclerViewAdapter(var arrayList: ArrayList<Currency>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), Filterable {
    lateinit var arrayListFilter: ArrayList<Currency>

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(v: View?) {}

        init {}

        private val itemIcon = itemView.findViewById<TextView>(R.id.item_icon)
        private val itemName = itemView.findViewById<TextView>(R.id.item_name)
        private val itemSymbol = itemView.findViewById<TextView>(R.id.item_symbol)

        fun bind(currency: Currency) {
            itemIcon?.text = currency.name.subSequence(0, 1)
            itemName?.text = currency.name
            itemSymbol?.text = currency.symbol
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.currency_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getFilter(): Filter {
        return filterFunc
    }

    var filterFunc: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Currency> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(arrayListFilter)
            } else {
                for (item in arrayListFilter) {
                    if (isContain(item, constraint)) {
                        filteredList.add(item)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            arrayList.clear()
            arrayList.addAll((results.values as Collection<Currency>))
            notifyDataSetChanged()
        }

        fun isContain(item: Currency, constraint: CharSequence?): Boolean {
            if (item.id.toLowerCase().contains(constraint.toString().toLowerCase()) || item.name.toLowerCase().contains(constraint.toString().toLowerCase()) || item.symbol.toLowerCase().contains(constraint.toString().toLowerCase())) {
                return true
            }

            return false
        }
    }

    init {
        arrayListFilter = ArrayList()
        arrayListFilter.addAll(arrayList)
    }
}
