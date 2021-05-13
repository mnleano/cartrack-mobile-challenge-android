package com.neds.cartrackmobilechallenge.ui

import android.content.Context
import com.facebook.soloader.SoLoader
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.neds.cartrackmobilechallenge.BuildConfig
import com.neds.cartrackmobilechallenge.data.entities.AppUser
import com.neds.cartrackmobilechallenge.data.entities.MyObjectBox
import com.neds.cartrackmobilechallenge.data.local.AccountPrefStore
import com.neds.cartrackmobilechallenge.data.local.LocalPreferences
import com.neds.cartrackmobilechallenge.data.remote.NetworkInterceptor
import com.neds.cartrackmobilechallenge.data.remote.UserService
import com.neds.cartrackmobilechallenge.data.repositories.LoginRepository
import com.neds.cartrackmobilechallenge.data.repositories.AccountRepository
import com.neds.cartrackmobilechallenge.data.repositories.UserRepository
import com.neds.cartrackmobilechallenge.data.viewModels.LoginViewModel
import com.neds.cartrackmobilechallenge.data.viewModels.SplashViewModel
import com.neds.cartrackmobilechallenge.data.viewModels.MainViewModel
import com.tencent.mmkv.MMKV
import io.objectbox.Box
import io.objectbox.BoxStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
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
    factory(named("appUserBox")) { get<BoxStore>().boxFor(AppUser::class.java) as Box<AppUser> }

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
    single { get<Retrofit>().create(UserService::class.java) as UserService }

    // Repository
    factory { AccountRepository(get(named("appUserBox")), get()) }
    factory { LoginRepository(get(named("appUserBox")), get()) }
    factory { UserRepository(get()) }

    // ViewModel
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }

}

