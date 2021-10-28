package com.sweeftdigital.countries.di

import com.sweeftdigital.countries.model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
}