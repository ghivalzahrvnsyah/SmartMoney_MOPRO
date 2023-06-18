package com.d3if3020.smartmoney.network

import com.d3if3020.smartmoney.model.BankList
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


private const val BASE_URL = "https://raw.githubusercontent.com/ghivalzahrvnsyah/SmartMoney_MOPRO/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BankListApiService {
    @GET("static-api.json")
    suspend fun getBankList(): List<BankList>

}
object BankListApi {
    val service: BankListApiService by lazy {
        retrofit.create(BankListApiService::class.java)
    }
    fun getBankListUrl(bankLogo: String): String {
        return "$BASE_URL$bankLogo.png"
    }
}
enum class ApiStatus {LOADING, SUCCESS, FAILED}