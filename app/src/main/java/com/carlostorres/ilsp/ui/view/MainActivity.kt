package com.carlostorres.ilsp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.ilsp.R
import com.carlostorres.ilsp.databinding.ActivityMainBinding
import com.carlostorres.ilsp.model.intrfacs.NumbersListener
import com.carlostorres.ilsp.ui.adapter.ItemsAdapter
import com.carlostorres.ilsp.ui.utils.hide
import com.carlostorres.ilsp.ui.utils.show
import com.carlostorres.ilsp.ui.utils.toast
import com.carlostorres.ilsp.ui.viewmodel.NumbersVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NumbersListener {

    // Databinding & ViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NumbersVM

    // RecyclerView & Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Databinding & ViewModel
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(NumbersVM::class.java)
        binding.home = viewModel
        viewModel.numbersListener = this

        createRecyclerView()
    }

    private fun createRecyclerView() {
        recyclerView = findViewById(R.id.rvLista)
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onSuccess(item: List<Int>) {

        if ( item.size < 100 ) {
            recyclerView.layoutManager = layoutManager
            adapter = ItemsAdapter(item)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

        } else {
            toast(item.size.toString())
        }
    }

    override fun onError() {
        toast(getString(R.string.error_data_input))
    }

    override fun onErrorConnection() {
        toast(getString(R.string.error_connection))
    }

    override fun onInvalidValues() {
        toast(getString(R.string.error_data_input))
    }

    override fun onBeGreaterThan() {
        toast(getString(R.string.error_number_mayor))
    }

    override fun showProgress() {
        pbHome.show()
    }

    override fun hideProgress() {
        pbHome.hide()
    }

}