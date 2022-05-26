package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.reduce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.ApiHelper
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseHelper
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils.Resource
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class ReduceViewModel(
    val apiHelper: ApiHelper,
    dbHelper: DatabaseHelper
) : ViewModel() {

    private val status = MutableLiveData<Resource<String>>()

    fun startReduceTask() {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            val result = (1..5).asFlow()
                .reduce { a, b -> a + b }

            status.postValue(Resource.success(result.toString()))
        }
    }

    fun getStatus(): LiveData<Resource<String>> {
        return status
    }

}
