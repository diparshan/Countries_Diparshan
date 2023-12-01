package com.example.countries_diparshan.api

import com.example.countries_diparshan.models.Country
import retrofit2.http.GET

interface MyInterface {

    @GET("/v3.1/independent?status=true")
    suspend fun getAllCountries(): List<Country>
}