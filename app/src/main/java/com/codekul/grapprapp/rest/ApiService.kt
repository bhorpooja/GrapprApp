package com.codekul.grapprapp.rest


import com.codekul.grapprapp.domain.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by pooja on 3/1/18.
 */
interface ApiService {
    @Headers("Accept: application/json","Content-Type: application/json")

    @GET("app/getApps")
    fun getApps(): Call<AppInfo>

    @POST("user/registerUser")
    fun registerUser(@Body user: User):Call<UserInfo>

    @POST("user/userLogin")
    fun userLogin(@Body credential: Credential):Call<LoginInfo>

    @POST("user/updateUser")
    fun updateUser(@Body update: Update):Call<UserInfo>

    companion object {

        private val PROTOCOL: String = "http"
        private val SERVER: String = "192.168.0.101"
        private val PORT: String = "8181"
        private val APP_NAME: String = "cashbolo/api"
        val BASE_URL = "$PROTOCOL://$SERVER:$PORT/$APP_NAME/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}