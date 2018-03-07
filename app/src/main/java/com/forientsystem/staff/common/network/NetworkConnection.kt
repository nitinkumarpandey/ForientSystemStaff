package com.forientsystem.staff.common.network

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.forientsystem.staff.common.interfaces.NotifyTaskDone
import com.forientsystem.staff.common.utils.AppConstants
import com.forientsystem.staff.models.DataModel

import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import java.nio.charset.Charset

/**
 * Created by qtech on 17/11/17.
 * This is common Network API class
 */

class NetworkConnection(var context: Context, var modelName: String, private var param: HashMap<String, String>, var taskDone: NotifyTaskDone, var showHide: Boolean) {
    private var client: AsyncHttpClient? = null

    var dialog: ProgressDialog? = null
    private var responseHandler: AsyncHttpResponseHandler? = null

    init {

        client = AsyncHttpClient()
        client?.setTimeout(60000)
        responseHandler = object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                if (statusCode == 200) {
                    val response = responseBody?.toString(Charset.defaultCharset())
                    println("Response:= $response")
                    val gson = Gson()

                    val cls = Class.forName(modelName) as Class<DataModel>
                    val model: DataModel? = try {
                        gson.fromJson(response, cls)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                    taskDone.onTaskDone(context, model)
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                Log.e("STATUS", statusCode.toString())
                error?.printStackTrace()
                taskDone.onTaskDone(context, null)
            }

            override fun onStart() {
                super.onStart()
                if (showHide)
                    dialog = ProgressDialog.show(context, "Loading", "Please Wait..", true, false)
            }

            override fun onFinish() {
                super.onFinish()
                if (showHide)
                    dialog?.dismiss()
            }

        }
    }

    fun getRequest() {
        val request = RequestParams(param)
        System.out.println("URLS:= " + AppConstants.URL + request.toString())
        client?.get(context, AppConstants.URL, request, responseHandler)
    }

    fun postRequest() {
        val request = RequestParams(param)
        System.out.println("URLS:= " + AppConstants.URL + request.toString())
        client?.post(context, AppConstants.URL, request, responseHandler)
    }

    fun cancelRequest() {
        client?.cancelAllRequests(true)
    }
}