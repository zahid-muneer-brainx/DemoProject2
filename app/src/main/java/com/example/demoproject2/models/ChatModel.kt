package com.example.demoproject2.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChatModel(
    @SerializedName("id")
    @Expose
    var id: String = "",
    @SerializedName("unread_count")
    @Expose
    var unread_count: Int = 0,
    @SerializedName("created_at")
    @Expose
    var created_at: String = "",
    @SerializedName("last_message")
    @Expose
    var last_message: MessageModel,
    @SerializedName("client")
    @Expose
    var client: ClientModel?
):Serializable {
}