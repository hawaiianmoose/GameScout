package hawaiianmoose.gamescout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.igdb.api_android_java.model.APIWrapper
import com.igdb.api_android_java.model.Parameters
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import com.igdb.api_android_java.callback.onSuccessCallback
import com.squareup.picasso.Picasso
import hawaiianmoose.gamescout.data.GameData
import hawaiianmoose.gamescout.data.TwitchResponse
import hawaiianmoose.gamescout.data.TwitchService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testvidyaApi()

        TwitchService(this).getTopGames().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<TwitchResponse> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(value: TwitchResponse?) {
                var imageView = findViewById<ImageView>(R.id.gameScreen)
                var title = findViewById<TextView>(R.id.title)

                title.text = value?.getTopGames()?.first()?.game?.name

                Picasso.with(baseContext)
                        .load(value?.getTopGames()?.first()?.game?.box?.large)
                        .into(imageView)
            }

            override fun onError(e: Throwable?) {
            }

        })
    }

    fun testvidyaApi() {
        val wrapper = APIWrapper(this, getString(R.string.API_Key))
        val params = Parameters()
                .addSearch("super smash brothers melee")
                .addFields("name, cover, screenshots")
                //.addOrder("published_at:desc")

//        var imageView = findViewById<ImageView>(R.id.gameScreen)
//
//        Picasso.with(baseContext)
//                .load("https://images.igdb.com/igdb/image/upload/t_screenshot_big/ebp44j3cgyonjxyoa5gp.jpg")
//                .into(imageView)

        wrapper.search(APIWrapper.Endpoint.GAMES, params, object : onSuccessCallback {
            override fun onSuccess(result: JSONArray) {
                var check = result.toJSONObject(result)

                val collectionType = object : TypeToken<Collection<GameData>>(){}.type
                val gson = Gson()
                val results = gson.fromJson<Collection<GameData>>(result.toString(), collectionType)
                var check2 = results
                var check3 = check2

                //https://images.igdb.com/igdb/image/upload/t_screenshot_med/ebp44j3cgyonjxyoa5gp.jpg

                var imageView = findViewById<ImageView>(R.id.gameScreen)

//                Picasso.with(baseContext)
//                        .load("https://images.igdb.com/igdb/image/upload/t_cover_big/ebp44j3cgyonjxyoa5gp.jpg")
//                        .resize(300,180)
//                        .into(imageView)

            }

            override fun onError(error: VolleyError) {
                // Do something on error
            }
        })
    }
}
