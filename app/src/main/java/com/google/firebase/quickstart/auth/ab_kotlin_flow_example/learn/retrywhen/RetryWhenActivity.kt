package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retrywhen
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.R
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.ApiHelperImpl
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.RetrofitBuilder
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseBuilder
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseHelperImpl
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils.Status
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_retry.*

class RetryWhenActivity : AppCompatActivity() {

    private lateinit var viewModel: RetryWhenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retry)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        viewModel.getStatus().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    textView.text = it.data
                    textView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.startTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(RetryWhenViewModel::class.java)
    }
}