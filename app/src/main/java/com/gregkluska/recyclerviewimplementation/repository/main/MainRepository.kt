package com.gregkluska.recyclerviewimplementation.repository.main

import androidx.lifecycle.LiveData
import com.gregkluska.recyclerviewimplementation.api.ApiService
import com.gregkluska.recyclerviewimplementation.api.GenericApiResponse
import com.gregkluska.recyclerviewimplementation.models.Photo
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    val apiService: ApiService
) {

    fun testPhotoListRequest(): LiveData<GenericApiResponse<List<Photo>>> {
        return apiService.getPhotos()
    }

}