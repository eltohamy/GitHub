package io.mbition.github.deps.factories

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Tohamy on 3/15/2018.
 */
object NetworkingFactory {
    inline fun <reified T> createService(debug: Boolean, baseUrl: HttpUrl): T {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (debug)
            logging.level = HttpLoggingInterceptor.Level.BODY
        else
            logging.level = HttpLoggingInterceptor.Level.NONE
        httpClient.addInterceptor(logging)
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient.build())
                .build()
                .create(T::class.java)
    }
}