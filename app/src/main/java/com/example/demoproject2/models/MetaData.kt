package com.example.demoproject2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MetaData(
    @SerializedName("current_page")
    @Expose
    var current_page:Int,
    @SerializedName("total_pages")
    @Expose
    var total_pages:Int
):Serializable
