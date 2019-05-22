package dk.nytmodultest.studieportal

class Host() {
    //val currentHostUrl: String = "http://192.168.8.100:8000"

    companion object{
        // API for server requests
        const val API = "http://10.0.2.2:8000/api/"
        //other uses (without /api/)
        const val currentHostUrl = "http://192.168.8.100:8000"
    }
}
