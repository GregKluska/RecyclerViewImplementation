package com.gregkluska.recyclerviewimplementation.api

import androidx.lifecycle.LiveData
import com.gregkluska.recyclerviewimplementation.models.Photo
import retrofit2.http.GET

interface ApiService {

    @GET("albums/1/photos")
    fun getPhotos(): LiveData<GenericApiResponse<List<Photo>>>

}