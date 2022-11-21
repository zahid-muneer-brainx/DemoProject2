package com.example.demoproject2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ClientModel(
    @SerializedName("name")
    @Expose
    var name: String = ""
) {
}