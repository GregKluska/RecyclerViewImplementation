package com.gregkluska.recyclerviewimplementation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gregkluska.recyclerviewimplementation.R
import com.gregkluska.recyclerviewimplementation.api.ApiEmptyResponse
import com.gregkluska.recyclerviewimplementation.api.ApiErrorResponse
import com.gregkluska.recyclerviewimplementation.api.ApiSuccessResponse
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
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
}