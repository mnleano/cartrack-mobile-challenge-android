package com.neds.cartrackmobilechallange.ui

import android.content.Context
import com.facebook.soloader.SoLoader
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neds.cartrackmobilechallange.BuildConfig
import com.neds.cartrackmobilechallange.data.entities.MyObjectBox
import com.neds.cartrackmobilechallange.data.local.AccountPrefStore
import com.neds.cartrackmobilechallange.data.local.LocalPreferences
import com.neds.cartrackmobilechallange.data.remote.NetworkInterceptor
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun initMMK(context: Context): MMKV {
    MMKV.initialize(context)
    val mmkv = MMKV.defaultMMKV(MMKV.SINGLE_PROCESS_MODE, "TODO: key-for-encryption")!!
    SoLoader.init(context, false)
    return mmkv
}

private fun initOkHttpClient(interceptor: NetworkInterceptor): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
        )
        .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
    return okHttpClientBuilder.build()
}

fun initGson(): Gson = GsonBuilder().setLenient().create()

fun initCoroutineScope(): CoroutineScope =
    CoroutineScope(Dispatchers.IO + SupervisorJob())

val modules = module {

    // Local - SharedPreferences
    single { Gson() }
    single { initMMK(get()) }
    factory { LocalPreferences(get(), get()) }
    factory { AccountPrefStore(get()) }

    // Database - ObjectBox
    single { MyObjectBox.builder().androidContext(get<Context>()).build() }

    // Remote - Web service
    single { initCoroutineScope() }
    single { NetworkInterceptor(get()) }
    single { initOkHttpClient(get()) }
    single {
        Retrofit
            .Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(initGson()))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    // Remote - Services


    // Repository


    // ViewModel


}

