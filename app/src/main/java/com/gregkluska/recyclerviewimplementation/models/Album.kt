package com.gregkluska.recyclerviewimplementation.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Album(
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("title")
    val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Album

        if (id != other.id) return false
        if (title != other.title) return false

        return true
    }

    override fun toString(): String {
        return "Album(id=$id, title='$title')"
    }
}