package com.gregkluska.recyclerviewimplementation.repository.main

import androidx.lifecycle.LiveData
import com.gregkluska.recyclerviewimplementation.api.ApiService
import com.gregkluska.recyclerviewimplementation.api.GenericApiResponse
import com.gregkluska.recyclerviewimplementation.models.Album
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.models.User
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val apiService: ApiService
) {

    fun getUsers(): LiveData<GenericApiResponse<List<User>>> {
        return apiService.getUsers();
    }

    fun getAlbums(userId: Int): LiveData<GenericApiResponse<List<Album>>> {
        return apiService.getAlbums(userId)
    }

    fun getPhotos(albumId: Int): LiveData<GenericApiResponse<List<Photo>>> {
        return apiService.getPhotos(albumId)
    }

}