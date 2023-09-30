package com.sheinahamisi.weathersagepro.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.sheinahamisi.weathersagepro.data.mappers.toOpenWeatherResponse
import com.sheinahamisi.weathersagepro.data.data_source.remote.OpenWeatherApi
import com.sheinahamisi.weathersagepro.core.util.Resource
import com.sheinahamisi.weathersagepro.data.util.safeApiCall
import com.sheinahamisi.weathersagepro.domain.model.OpenWeatherResponse
import com.sheinahamisi.weathersagepro.domain.repository.WeatherRepository
import com.sheinahamisi.weathersagepro.domain.util.CurrentLocationResult
import com.sheinahamisi.weathersagepro.domain.util.NetworkStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val openWeatherApi: OpenWeatherApi,
    private val context: Context
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Flow<Resource<OpenWeatherResponse>> {
        return safeApiCall { openWeatherApi.getCurrentWeather(lat, lon).toOpenWeatherResponse() }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override suspend fun getCurrentLocation(): Flow<CurrentLocationResult> {
        val locationProvider = LocationServices.getFusedLocationProviderClient(context)

        return callbackFlow {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                500
            ).apply {
                setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                setMinUpdateDistanceMeters(20f)
                setWaitForAccurateLocation(true)
            }.build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    locationResult.lastLocation?.let {
                        trySend(
                            CurrentLocationResult.Success(
                                it.latitude,
                                it.longitude
                            )
                        )
                    }
                }

                override fun onLocationAvailability(locationAvailability: LocationAvailability) {
                    super.onLocationAvailability(locationAvailability)
                    if (locationAvailability.isLocationAvailable.not()) {
                        trySend(CurrentLocationResult.Error)
                    } else {
                        trySend(CurrentLocationResult.Retry)
                    }
                }
            }

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                trySend(CurrentLocationResult.RequestPermission)
            }

            locationProvider.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                locationProvider.removeLocationUpdates(locationCallback)
            }
        }.distinctUntilChanged()
    }

    override suspend fun getNetworkStatus(): Flow<NetworkStatus> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return callbackFlow {
            val networkCallback = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(NetworkStatus.Available)
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    trySend(NetworkStatus.Losing)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(NetworkStatus.Lost)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(NetworkStatus.Unavailable)
                }
            }

            connectivityManager.registerDefaultNetworkCallback(networkCallback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }

        }.distinctUntilChanged()
    }
}