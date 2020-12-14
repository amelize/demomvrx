package ru.sebely.demoapplication.network

import retrofit2.http.GET
import retrofit2.http.Headers
import ru.sebely.demoapplication.model.MenuResponse
import ru.sebely.demoapplication.model.PromoResponse


interface DeliveryService {
    @Headers("Accept: application/json")
    @GET("search")
    suspend fun promoList(
    ): PromoResponse

    @Headers("Accept: application/json")
    @GET("menu")
    suspend fun menu(
    ): MenuResponse
}