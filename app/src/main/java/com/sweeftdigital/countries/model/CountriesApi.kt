package com.sweeftdigital.countries.model

import com.sweeftdigital.countries.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>

}