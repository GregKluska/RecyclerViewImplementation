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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Photo

        if (id != other.id) return false
        if (title != other.title) return false
        if (url != other.url) return false
        if (thumbnailUrl != other.thumbnailUrl) return false

        return true
    }

}