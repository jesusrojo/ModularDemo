//package com.jesusrojo.modulardemo.di
//
//import android.content.Context
//import com.jakewharton.espresso.OkHttp3IdlingResource
//import com.jesusrojo.remote.api.RawDatasApiService
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//// RETROFIT
//private val okHttp2Client: OkHttpClient.Builder = OkHttpClient.Builder()
//    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//
//private val client = okHttp2Client.build()
//
//// TESTING
//val idlingResource = OkHttp3IdlingResource.create("okhttp", client)
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//class NetModule {
//
//    // RETROFIT
//    val BASE_URL = "https://api.openweathermap.org/data/2.5/"
//
////    private val okHttp2Client: OkHttpClient.Builder = OkHttpClient.Builder()
////        .addInterceptor(
////            HttpLoggingInterceptor()
////                .setLevel(HttpLoggingInterceptor.Level.BODY)
////        )
////    private val client = okHttp2Client.build()
//
//    @Provides
//    @Singleton
//    fun networkInterceptor(): Interceptor = Interceptor { chain ->
//        val request = chain.request()
//        val originalHttpUrl = request.url
//
//        val url = originalHttpUrl.newBuilder()
//          //  .addQueryParameter("appid", BuildConfig.MY_API_KEY)
//            .build()
//
//        val builder = request.newBuilder()
//            .addHeader("Accept-Language", "en-US")
//            .addHeader("Content-Type", "application/json")
//            .url(url)
//            .build()
//
//        return@Interceptor chain.proceed(builder)
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(
//        @ApplicationContext context: Context,
//        interceptor: Interceptor
//    ): OkHttpClient =
//        OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
//            .addInterceptor(interceptor)
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .build()
//
//    @Provides
//    @Singleton
//    fun providesRetrofit(client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .client(client)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRawDatasApiService(retrofit: Retrofit): RawDatasApiService =
//        retrofit.create(RawDatasApiService::class.java)
//
//}