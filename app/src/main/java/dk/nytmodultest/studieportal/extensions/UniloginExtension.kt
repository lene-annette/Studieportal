package dk.nytmodultest.studieportal.extensions

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log.d
import android.util.Base64
import dk.nytmodultest.studieportal.Config
import java.net.URLEncoder
import java.security.MessageDigest

class UniloginExtension(private val url: String = "none"){
    private fun getParamsSection():String{
        val split = url.split("?")
        return split[1]
    }

    fun getParam(param: String):String{
        val params = getParamsSection()
        var found = false
        var count = 0
        var result = ""
        val split = params.split("&")

        while(found == false || count < split.size){
            if(split[count].contains(param)){
                result = split[count]
                found = true
            }
            count++
        }

        val resultSplit = result.split("=")
        return resultSplit[1]
    }

    fun toUnilogin():String{
        var loginUrl = "https://sso.emu.dk/unilogin/login.cgi?"
        val returnUrl = "${Config.FRONTEND}app-unilogin"
        val appId = "ssomobius"
        val secret = "edxjv3@PU77Ajc\$L"
        val md5 = toMd5Hash(returnUrl+secret)

        val data = returnUrl.toByteArray()
        val base64 = Base64.encodeToString(data, Base64.DEFAULT)
        var encoded = URLEncoder.encode(base64,"UTF-8")
        encoded = encoded.dropLast(3)

        return loginUrl + "id=" + appId + "&path=" + encoded + "&auth=" + md5
    }

    private fun toMd5Hash(text: String): String{
        var result = ""
        try{
            val md5 = MessageDigest.getInstance("MD5")
            val md5HashBytes = md5.digest(text.toByteArray()).toTypedArray()

            result =  byteArrayToHexString(md5HashBytes)
        }catch(e: Exception){
            result = "error: ${e.message}"
        }
        return result
    }

    private fun byteArrayToHexString(array: Array<Byte>): String {
        var result = StringBuilder()
        for(byte in array){
            val toAppend = String.format("%2X", byte).replace(" ","0")
            result.append(toAppend)
        }
        return result.toString().toLowerCase()
    }



}