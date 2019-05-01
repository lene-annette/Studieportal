package dk.nytmodultest.studieportal.data.db

class StudentInfo(var map: MutableMap<String, Any?>){
    var _id: Long by map
    var id: Long by map
    var firstName: String by map
    var lastName: String by map
    var userName: String by map
    var email: String by map
    var phoneNr: String by map
    var citizenship: String by map
    var last5yCountry: String by map
    var birthCountry: String by map
    var motherTongue: String by map

    constructor(id: Long, firstName:String, lastName:String, userName:String, email:String, phoneNr:String,
                citizenship:String, last5yCountry:String, birthCountry:String, motherTongue:String): this(HashMap()) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
        this.email = email
        this.phoneNr = phoneNr
        this.citizenship = citizenship
        this.last5yCountry = last5yCountry
        this.birthCountry = birthCountry
        this.motherTongue = motherTongue
    }
}