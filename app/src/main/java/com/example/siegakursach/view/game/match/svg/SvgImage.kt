//package com.example.siegakursach.view.game.match.svg
//
//import android.R
//
//import android.content.Context;
//import android.widget.ImageView;
//import com.pixplicity.sharp.Sharp;
//import java.io.IOException;
//import java.io.InputStream;
//import okhttp3.Cache;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//
//
//class SvgImage {
//    private var httpClient: OkHttpClient? = null
//
//    fun fetchSvg(
//        context: Context,
//        url: String?,
//        target: ImageView
//    ) {
//        if (httpClient == null) {
//            httpClient = OkHttpClient.Builder()
//                .cache(
//                    Cache(
//                        context.getCacheDir(),
//                        5 * 1024 * 1014
//                    )
//                )
//                .build()
//        }
//
//        val request = Request.Builder().url(url).build()
//        httpClient!!.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call?, e: IOException?) {
//                target.setImageResource(
//                    R.drawable.ic_delete
//                )
//            }
//
//            @Throws(IOException::class)
//            override fun onResponse(
//                call: Call?,
//                response: Response
//            ) {
//                // sharp is a library which will load stream
//                // which we generated from url in our target
//                // imageview.
//                val stream: InputStream = response.body()!!.byteStream()
//                Sharp.loadInputStream(stream).into(target)
//                stream.close()
//            }
//        })
//    }
//}