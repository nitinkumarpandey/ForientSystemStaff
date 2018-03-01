package com.forientsystem.staff.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.forientsystem.staff.R
import com.forientsystem.staff.models.Task

/**
 * Created by nitin on 3/1/18.
 */
const val EMPTY = 0
const val NORMAL = 1

class TasksAdapter(var context: Context, var dataList: ArrayList<Task>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType== EMPTY)
            EmptyViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false))
        else
            NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.row_task,parent,false))

    }

    override fun getItemCount(): Int {
        return if (dataList.isEmpty()) 1 else dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList.isEmpty()) EMPTY else NORMAL
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    }

    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}