package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.ApiHelper
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseHelper
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils.Resource

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FilterViewModel(
    apiHelper: ApiHelper,
    dbHelper: DatabaseHelper
) : ViewModel() {
    private val status = MutableLiveData<Resource<String>>()

    fun startFilterTask() {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            val result = mutableListOf<Int>()
            (1..5).asFlow()
                .filter {
                    it % 2 == 0
                }
                .toList(result)

            status.postValue(Resource.success(result.toString()))
        }
    }

    fun getStatus(): LiveData<Resource<String>> {
        return status
    }

}