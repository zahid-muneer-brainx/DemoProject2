package com.example.demoproject2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    @Expose
    var id: Int? = 0,
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("uid")
    @Expose
    var uid: String? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("device_token")
    @Expose
    var device_token: String? = null,

    @SerializedName("app_platform")
    @Expose
    var app_platform: String? = null,

    @SerializedName("app_version")
    @Expose
    var app_version: String? = null,

    @SerializedName("active?")
    @Expose
    var active: Boolean? = false,

    @SerializedName("first_login?")
    @Expose
    var first_login: Boolean? = false,


) {


}
