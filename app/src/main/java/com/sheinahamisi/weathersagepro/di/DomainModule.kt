package com.sheinahamisi.weathersagepro.di

import com.sheinahamisi.weathersagepro.domain.repository.WeatherRepository
import com.sheinahamisi.weathersagepro.domain.use_case.GetCurrentWeather
import com.sheinahamisi.weathersagepro.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideGetCurrentWeather(repository: WeatherRepository) = GetCurrentWeather(repository)

    @Provides
    @ViewModelScoped
    fun provideUseCases(
        getCurrentWeather: GetCurrentWeather
    ) = UseCases(
        getCurrentWeather = getCurrentWeather
    )

}