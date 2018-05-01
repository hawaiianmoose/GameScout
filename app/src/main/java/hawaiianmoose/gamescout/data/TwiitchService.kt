package hawaiianmoose.gamescout.data

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import hawaiianmoose.gamescout.R
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.Request

class TwitchService(context: Context) {
    val dataUrl: String = "https://api.twitch.tv/kraken/games/top?client_id=" + context.getString(R.string.Twitch_client_id)
    val client = OkHttpClient()

    fun getTopGames(): Observable<TwitchResponse> {
        return Observable.create {
            subscriber ->

            val request = Request.Builder().url(dataUrl).build()
            val response = client.newCall(request).execute()
            val gson = Gson()
            val results = gson.fromJson<TwitchResponse>(response.body()?.string(), TwitchResponse::class.java)

            subscriber.onNext(results)
            subscriber.onComplete()
        }
    }

    fun fetchTopGames() {
        Observable.just(getTopGames())
    }
}
