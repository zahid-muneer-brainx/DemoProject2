package com.example.demoproject2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MessageModel(
    @SerializedName("id")
    @Expose
    var id: Int? = 0,
    @SerializedName("type")
    @Expose
    var type: String="",
    @SerializedName("text")
    @Expose
    var text: String = "",
    @SerializedName("read?")
    @Expose
    var read: Boolean = false,
    @SerializedName("attachment?")
    @Expose
    var attachment: Boolean = false,
    @SerializedName("client_number")
    @Expose
    var client_number: String = "",
    @SerializedName("attachment_url")
    @Expose
    var attachment_url: String? = "",
    @SerializedName("created_at")
    @Expose
    var created_at: String = "",
):Serializable {
}