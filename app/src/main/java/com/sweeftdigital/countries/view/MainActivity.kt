package com.sweeftdigital.countries.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweeftdigital.countries.databinding.ActivityMainBinding
import com.sweeftdigital.countries.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        with(binding) {
            rvCountriesList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = countriesAdapter
            }
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                viewModel.refresh()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                binding.rvCountriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let {
                binding.tvListError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                binding.pbLoadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.tvListError.visibility = View.GONE
                    binding.rvCountriesList.visibility = View.GONE
                }
            }
        })
    }
}