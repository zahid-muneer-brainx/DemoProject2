package com.example.demoproject2.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ChatResponseModel(
    @SerializedName("threads")
    var chatModel:ArrayList<ChatModel>,
    @SerializedName("meta")
    var metaData: MetaData
):Serializable {

}