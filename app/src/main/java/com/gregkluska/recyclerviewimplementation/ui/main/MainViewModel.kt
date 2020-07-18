package com.gregkluska.recyclerviewimplementation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gregkluska.recyclerviewimplementation.api.GenericApiResponse
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.repository.main.MainRepository

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun testPhotoListRequest(): LiveData<GenericApiResponse<List<Photo>>> {
        return mainRepository.testPhotoListRequest()
    }

}