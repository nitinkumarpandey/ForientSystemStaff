package com.forientsystem.staff.common.interfaces

import android.content.Context
import com.forientsystem.staff.models.DataModel

/**
 * Created by qtech on 17/11/17.
 */

interface NotifyTaskDone {
    fun onTaskDone(context: Context, data: DataModel?)
}