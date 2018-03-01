package com.forientsystem.staff.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.forientsystem.staff.R
import com.forientsystem.staff.adapters.TasksAdapter
import com.forientsystem.staff.models.Task
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {


    var taskList = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        onRefresh()
    }

    override fun onRefresh() {

        swipeRefresher.isRefreshing = true
        getTaskList()
    }

    private fun getTaskList() {

    }

    fun setupRecyclerView() {
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.adapter = TasksAdapter(this, taskList)

    }

}
