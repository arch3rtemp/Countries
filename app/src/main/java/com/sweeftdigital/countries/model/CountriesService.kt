package com.sweeftdigital.countries.model

import com.sweeftdigital.countries.Country
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {
    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent
    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}