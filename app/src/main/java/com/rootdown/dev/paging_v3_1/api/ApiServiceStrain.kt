package com.rootdown.dev.paging_v3_1.api


import retrofit2.http.GET

interface ApiServiceStrain {
    @GET("api/strainls")
    suspend fun getStrainsList(): NetworkStrainContainer
}