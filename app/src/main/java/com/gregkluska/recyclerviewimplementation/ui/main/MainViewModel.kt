package com.gregkluska.recyclerviewimplementation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.gregkluska.recyclerviewimplementation.api.GenericApiResponse
import com.gregkluska.recyclerviewimplementation.models.Album
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.models.User
import com.gregkluska.recyclerviewimplementation.repository.main.MainRepository
import com.gregkluska.recyclerviewimplementation.util.AbsentLiveData
import com.gregkluska.recyclerviewimplementation.util.MergedData

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val mUserList : LiveData<GenericApiResponse<List<User>>> = mainRepository.getUsers()
    private val mAlbumList : LiveData<GenericApiResponse<List<Album>>>
    private val mPhotoList : LiveData<GenericApiResponse<List<Photo>>>

    private val mUserId : MutableLiveData<Int> = MutableLiveData()
    private val mAlbumId : MutableLiveData<Int> = MutableLiveData()
    private val mMergedData : MediatorLiveData<MergedData> = MediatorLiveData()

    init {

        mAlbumList = Transformations.switchMap(mUserId) {
            mUserId.value?.let { mainRepository.getAlbums(it) }
                ?: AbsentLiveData.create()
        }

        mPhotoList = Transformations.switchMap(mAlbumId) {
            mAlbumId.value?.let { mainRepository.getPhotos(it) }
                ?: AbsentLiveData.create()
        }

        mMergedData.addSource(mUserList) {
            it?.let { response ->
                mMergedData.value = MergedData.UserList(response)
            }
        }

        mMergedData.addSource(mAlbumList) {
            it?.let { response ->
                mMergedData.value = MergedData.AlbumList(response)
            }
        }

        mMergedData.addSource(mPhotoList) {
            it?.let { response ->
                mMergedData.value = MergedData.PhotoList(response)
            }
        }
    }

    fun postUserId(userId: Int) = mUserId.postValue(userId)
    fun postAlbumsId(albumId: Int) = mAlbumId.postValue(albumId)

    fun getMergedData() = mMergedData
}