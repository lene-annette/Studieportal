package dk.nytmodultest.studieportal.extensions

import android.util.Log.d
import com.google.gson.Gson
import java.util.*

class Decoder(val token:String, val gson: Gson = Gson()){
    fun decode():Long{
        d("Lene","In decoder, token: " + token)
        val split = token.split(".")
        val header = getJson(split[0])
        val body = getJson(split[1])
        d("Lene","header: " + header)
        d("Lene", "body: " + body)
        val info = gson.fromJson(body, Body::class.java)

        return info.id
    }

    private fun getJson(encoded: String):String{
        val decoder = Base64.getUrlDecoder()
        val decodedBytes = decoder.decode(encoded)
        return String(decodedBytes,charset("UTF_8"))

    }

    private class Body(val id: Long,val iat:Long, val exp: Long )
}