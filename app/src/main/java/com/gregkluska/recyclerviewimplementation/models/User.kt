package com.gregkluska.recyclerviewimplementation.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("username")
    val username: String,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("phone")
    val phone: String,

    @Expose
    @SerializedName("website")
    val website: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (username != other.username) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        if (website != other.website) return false

        return true
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', username='$username', email='$email', phone='$phone', website='$website')"
    }
}