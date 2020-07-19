package com.gregkluska.recyclerviewimplementation.util

import com.gregkluska.recyclerviewimplementation.api.GenericApiResponse
import com.gregkluska.recyclerviewimplementation.models.Album
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.models.User

sealed class MergedData {
    data class UserList(val userList: GenericApiResponse<List<User>>): MergedData()
    data class AlbumList(val albumList: GenericApiResponse<List<Album>>): MergedData()
    data class PhotoList(val photoList: GenericApiResponse<List<Photo>>): MergedData()
}