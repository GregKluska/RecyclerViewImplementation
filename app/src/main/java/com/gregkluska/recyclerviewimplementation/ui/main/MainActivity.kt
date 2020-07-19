package com.gregkluska.recyclerviewimplementation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.gregkluska.recyclerviewimplementation.R
import com.gregkluska.recyclerviewimplementation.api.ApiEmptyResponse
import com.gregkluska.recyclerviewimplementation.api.ApiErrorResponse
import com.gregkluska.recyclerviewimplementation.api.ApiSuccessResponse
import com.gregkluska.recyclerviewimplementation.models.Album
import com.gregkluska.recyclerviewimplementation.models.Photo
import com.gregkluska.recyclerviewimplementation.models.User
import com.gregkluska.recyclerviewimplementation.util.MergedData
import com.gregkluska.recyclerviewimplementation.util.TopSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainRecyclerAdapter.Interaction {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerAdapter: MainRecyclerAdapter
    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)

            val topSpacingDecorator = TopSpacingItemDecoration(30)
            removeItemDecoration(topSpacingDecorator) // does nothing if not applied already
            addItemDecoration(topSpacingDecorator)

            recyclerAdapter = MainRecyclerAdapter(
                requestManager,
                this@MainActivity
            )
            
            adapter = recyclerAdapter
        }
    }

    private fun subscribeObservers() {

        viewModel.getMergedData().observe(this, Observer { mergedData ->

            when(mergedData) {
                is MergedData.UserList -> {
                    Log.d(TAG, "subscribeObservers: ${mergedData.userList}")

                    when(mergedData.userList) {
                        is ApiSuccessResponse -> {
                            recyclerAdapter.apply {
                                submitUserList(mergedData.userList.body)
                            }
                        }

                        is ApiErrorResponse -> {
                            Log.d(TAG, "subscribeObservers: Error response : ${mergedData.userList.errorMessage}")
                        }

                        is ApiEmptyResponse -> {
                            Log.d(TAG, "subscribeObservers: Empty response")
                        }
                    }


                }
                is MergedData.AlbumList -> {
                    Log.d(TAG, "subscribeObservers: ${mergedData.albumList}")

                    when(mergedData.albumList) {
                        is ApiSuccessResponse -> {
                            recyclerAdapter.apply {
                                submitAlbumList(mergedData.albumList.body)
                            }
                        }

                        is ApiErrorResponse -> {
                            Log.d(TAG, "subscribeObservers: Error response : ${mergedData.albumList.errorMessage}")
                        }

                        is ApiEmptyResponse -> {
                            Log.d(TAG, "subscribeObservers: Empty response")
                        }
                    }
                }
                is MergedData.PhotoList -> {
                    Log.d(TAG, "subscribeObservers: ${mergedData.photoList}")

                    when(mergedData.photoList) {
                        is ApiSuccessResponse -> {
                            recyclerAdapter.apply {
                                submitPhotoList(mergedData.photoList.body)
                            }
                        }

                        is ApiErrorResponse -> {
                            Log.d(TAG, "subscribeObservers: Error response : ${mergedData.photoList.errorMessage}")
                        }

                        is ApiEmptyResponse -> {
                            Log.d(TAG, "subscribeObservers: Empty response")
                        }
                    }
                }

            }

        })

    }

    override fun onBackPressed() {
        when(viewModel.getMergedData().value) {
            is MergedData.AlbumList -> {
                viewModel.repostUsers()
            }

            is MergedData.PhotoList -> {
                viewModel.repostAlbums()
            }

            is MergedData.UserList -> {
                super.onBackPressed()
            }
        }
    }

    override fun onAlbumSelected(position: Int, album: Album) {
        viewModel.postAlbumsId(album.id)
    }

    override fun onUserSelected(position: Int, user: User) {
        viewModel.postUserId(user.id)
    }
}