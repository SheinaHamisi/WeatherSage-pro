package com.sheinahamisi.weathersagepro.di

import android.content.Context
import com.sheinahamisi.weathersagepro.BuildConfig
import com.sheinahamisi.weathersagepro.data.data_source.remote.OpenWeatherApi
import com.sheinahamisi.weathersagepro.data.repository.DefaultWeatherRepository
import com.sheinahamisi.weathersagepro.data.util.Utils
import com.sheinahamisi.weathersagepro.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(client: OkHttpClient): OpenWeatherApi {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: OpenWeatherApi,
        @ApplicationContext context: Context
    ): WeatherRepository = DefaultWeatherRepository(api, context)

}