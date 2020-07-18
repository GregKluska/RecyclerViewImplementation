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
import com.gregkluska.recyclerviewimplementation.models.Photo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity
@Inject
constructor(
    private val requestManager: RequestManager
) : AppCompatActivity(), MainRecyclerAdapter.Interaction {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        recycler_view.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            
            val recyclerAdapter = MainRecyclerAdapter(
                requestManager as RequestManager,
                this@MainActivity
            )
            
            adapter = recyclerAdapter
        }
    }

    private fun subscribeObservers() {

        viewModel.testPhotoListRequest().observe(this, Observer { response ->
            when(response) {

                is ApiSuccessResponse -> {
                    Log.d(TAG, "subscribeObservers: Success response : ${response.body}")
                }

                is ApiErrorResponse -> {
                    Log.d(TAG, "subscribeObservers: Error response : ${response.errorMessage}")
                }

                is ApiEmptyResponse -> {
                    Log.d(TAG, "subscribeObservers: Empty response")
                }

            }
        })

    }

    override fun onItemSelected(position: Int, item: Photo) {
        Log.d(TAG, "onItemSelected: Clicked item at position $position")
    }
}