package com.forientsystem.staff.models

import com.google.gson.annotations.SerializedName

/**
 * Created by nitin on 3/1/18.
 */
class LoginModel : DataModel() {
    var message: Boolean = false
    var errorMessage: String? = null
    var userdata: User? = null

    inner class User {
        var id: String? = null
        @SerializedName("user_name")
        var userName: String? = null
        @SerializedName("passwd")
        var password: String? = null
        @SerializedName("admin_type")
        var adminType: String? = null
        @SerializedName("fname")
        var firstName: String? = null
        @SerializedName("lname")
        var lastName: String? = null
    }
}