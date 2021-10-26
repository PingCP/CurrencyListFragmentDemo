package com.example.currencylistdemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencylistdemo.adapter.RecyclerViewAdapter
import com.example.currencylistdemo.database.Currency
import kotlinx.android.synthetic.main.fragment_currency_list.*

class CurrencyListFragment : Fragment() {
    private val TAG = "Apple_" + CurrencyListFragment::class.java.simpleName
    var adapter: RecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyViewModel: CurrencyViewModel by requireActivity().viewModels {
            CurrencyViewModelFactory((requireActivity().application as CurrencyApplication).repository)
        }
        search_button.setOnClickListener {
            Log.d(TAG, "click " + search_text!!.text)
            adapter?.filter?.filter(search_text!!.text)
        }

        search_text!!.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count:Int) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (s.isEmpty()) {
                    adapter?.filter?.filter(s)
                }
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        read_more_icon!!.setOnClickListener {
            currencyViewModel.allCurrency.observe(requireActivity(), Observer { currency ->
                // Update the cached copy of the words in the adapter.
                currency?.let {
                    Log.d(TAG, "it length: " + it.size)
                    hint_text!!.visibility = View.INVISIBLE
                    val arrayList = ArrayList<Currency>()
                    arrayList.addAll(it)
                    adapter = RecyclerViewAdapter(arrayList)
                    recyclerView.adapter = adapter
                }
            })
        }

        sort_icon!!.setOnClickListener {
            Log.d(TAG, "click sortButton")
            if (adapter != null) {
                adapter!!.arrayList.sortBy {
                    it.name
                }

                adapter!!.notifyDataSetChanged()
            }
        }
    }
}