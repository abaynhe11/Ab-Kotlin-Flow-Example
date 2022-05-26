package com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.retrofit.single

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.R
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.ApiHelperImpl
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.api.RetrofitBuilder
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseBuilder
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.local.DatabaseHelperImpl
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.data.model.ApiUser
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.learn.base.ApiUserAdapter
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils.Status
import com.google.firebase.quickstart.auth.ab_kotlin_flow_example.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_recycler_view.*

class SingleNetworkCallActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleNetworkCallViewModel
    private lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(SingleNetworkCallViewModel::class.java)
    }
}