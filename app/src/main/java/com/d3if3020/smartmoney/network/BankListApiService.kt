package com.d3if3020.smartmoney.network

import com.d3if3020.smartmoney.model.BankList
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val BASE_URL = "https://raw.githubusercontent.com/ghivalzahrvnsyah/SmartMoney_MOPRO/static-api/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BankListApiService {
    @GET("static-api.json")
    suspend fun getBankList(): String

}
object BankListApi {
    val service: BankListApiService by lazy {
        retrofit.create(BankListApiService::class.java)
    }
    fun getBankListUrl(imageId: String): String {
        return "$imageId.png"
    }
}