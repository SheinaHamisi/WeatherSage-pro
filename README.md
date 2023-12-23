# WeatherSagePro

A weather application designed to fetch the user's location and display the forecast for the current day.

API : [OpenWeatherMap](https://openweathermap.org/api)

Utilizing the OpenWeatherMap API offers several advantages, including:

- **Generous allowance of 1000 free API calls daily:** Ideal for small-scale projects.
- **Customization of units in requests:** Provides formatted responses based on preferred units (imperial/metric).
- **Streamlining data retrieval:** Flexibility to specify the required information in a single call, encompassing icons for various weather conditions.
- **Support for multiple languages:** Enables broader audience adoption.
- **Substantial user base:** OpenWeatherMap boasts a user base capable of handling millions of requests, instilling confidence in sustained high availability if the application scales.
- **Alert features:** The API includes notable features for issuing alerts concerning severe weather conditions.

# Configuration Instructions

To set up the application, follow these steps:

1. In your `local.properties` file, add your Open Weather API key:

   ```properties
   OPEN_WEATHER_API_KEY=YOUR_KEY
   OPEN_WEATHER_BASE_URL=https://api.openweathermap.org
   OPEN_WEATHER_ICONS_URL=http://openweathermap.org/img/wn/

Make sure to obtain an API key; you can check for one under "Api Keys."

# Environment
Ensure you have JDK 17 installed.
