## What's CryptoTracker?
<img src="https://github.com/Anass001/CryptoTracker/blob/main/main_screen.png" align="right" width="300">

CryptoTracker is a demo Android application for tracking cryptocurrency price changes.

## Used Libraries
 - [Jetpack Compose](https://developer.android.com/jetpack/compose) (Decleartive UI)
 - [Compose Destinations](https://github.com/raamcosta/compose-destinations) (Screen transitions)
 - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) (Store and manage UI-related data)
 - [Kotlin Flows](https://developer.android.com/kotlin/flow) (Handling asynchronous streams of data)
 - [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) (Light-weight threads)
 - [Hilt](https://dagger.dev/hilt/) (Dependency injection)
 - [Coil](https://github.com/coil-kt/coil) (Image loading)
 - [Room](https://developer.android.com/topic/libraries/architecture/room) (Abstraction layer over SQLite)
 - [Retrofit](https://github.com/square/retrofit) (HTTP client)
 - [Moshi](https://github.com/square/moshi) (JSON parsing)

## Architechture

The application is designed as a single-activity app and implements the MVVM architecture, following the guidelines provided [here](https://developer.android.com/jetpack/docs/guide).

<img src="https://github.com/Anass001/CryptoTracker/blob/main/architecture.png" align="left" width="360">

</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

## API
The application uses two APIs to retrieve data for its features:
- [CoinMarketCap API](https://coinmarketcap.com/api/), which is used to obtain the latest market data for all active cryptocurrencies. This API provides information such as the current price, market capitalization, and volume change for each currency.
- [CoinAPI](https://www.coinapi.io/), used to retrieve OHLCV data, which is then used to draw graphs for each cryptocurrency. OHLCV data provides a way to visualize the price movements of a particular asset over a specified period. 
