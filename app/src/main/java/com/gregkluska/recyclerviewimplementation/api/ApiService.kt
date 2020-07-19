package com.gregkluska.recyclerviewimplementation.api

import androidx.lifecycle.LiveData
import com.gregkluska.recyclerviewimplementation.models.Album
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("albums/{id}/photos")
    fun getPhotos(
        @Path("id")
        userId: Int
    ): LiveData<GenericApiResponse<List<Photo>>>

    @GET("/user/{id}/albums/")
    fun getAlbums(
        @Path("id")
        userId: Int
    ): LiveData<GenericApiResponse<List<Album>>>

    @GET("/users")
    fun getUsers() : LiveData<GenericApiResponse<List<User>>>

}