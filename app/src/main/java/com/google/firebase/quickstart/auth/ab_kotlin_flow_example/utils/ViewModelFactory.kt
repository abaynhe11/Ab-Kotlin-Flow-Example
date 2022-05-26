package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.ApiHelper
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseHelper
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.completion.CompletionViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.errorhandling.catch.CatchViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.errorhandling.emitall.EmitAllViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.filter.FilterViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.map.MapViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.reduce.ReduceViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retrofit.parallel.ParallelNetworkCallsViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retrofit.series.SeriesNetworkCallsViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retrofit.single.SingleNetworkCallViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retry.RetryViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retryexponentialbackoff.RetryExponentialBackoffModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retrywhen.RetryWhenViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.room.RoomDBViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.task.onetask.LongRunningTaskViewModel
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.task.twotasks.TwoLongRunningTasksViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(CatchViewModel::class.java)) {
            return CatchViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(EmitAllViewModel::class.java)) {
            return EmitAllViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
            return LongRunningTaskViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(TwoLongRunningTasksViewModel::class.java)) {
            return TwoLongRunningTasksViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(CompletionViewModel::class.java)) {
            return CompletionViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(FilterViewModel::class.java)) {
            return FilterViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(ReduceViewModel::class.java)) {
            return ReduceViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RetryViewModel::class.java)) {
            return RetryViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RetryWhenViewModel::class.java)) {
            return RetryWhenViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RetryExponentialBackoffModel::class.java)) {
            return RetryExponentialBackoffModel(apiHelper, dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}