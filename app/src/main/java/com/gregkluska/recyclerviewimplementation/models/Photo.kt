package com.gregkluska.recyclerviewimplementation.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo (

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("url")
    val url: String,

    @Expose
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String

) {
    override fun toString(): String {
        return "Photo(id=$id, title='$title', url='$url', thumbnailUrl='$thumbnailUrl')"
    }
}