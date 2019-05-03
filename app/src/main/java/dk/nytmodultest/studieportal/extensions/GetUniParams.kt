package dk.nytmodultest.studieportal.extensions

class GetUniParams(private val url: String){
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
}