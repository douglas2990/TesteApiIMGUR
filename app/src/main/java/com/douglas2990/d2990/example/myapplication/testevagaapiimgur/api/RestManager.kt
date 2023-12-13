package com.douglas2990.d2990.example.myapplication.testevagaapiimgur.api

import androidx.viewbinding.BuildConfig
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.model.ImgurModelCats
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class RestManager{
    companion object {

        private fun createHttpClient(): OkHttpClient.Builder {

            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logInterceptor)
            }
            return client
        }

        fun getEndpoints(): IEndpoints {
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createHttpClient().build())
                .build()
            return retrofit.create(IEndpoints::class.java)
        }

        interface IEndpoints {
            @GET("gallery/search/?q=cats")
            fun getCats(): Call<ImgurModelCats>
            //fun getCats(): Response<ImgurModelCats>

        }
    }
}