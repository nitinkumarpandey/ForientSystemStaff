package com.forientsystem.staff.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.forientsystem.staff.R
import com.forientsystem.staff.common.interfaces.NotifyTaskDone
import com.forientsystem.staff.common.network.NetworkConnection
import com.forientsystem.staff.common.utils.AppConstants
import com.forientsystem.staff.common.utils.AppSharedPreferences
import com.forientsystem.staff.models.DataModel
import com.forientsystem.staff.models.LoginModel
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), NotifyTaskDone {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            if (textInputUsername.editText?.text.isNullOrBlank()) {
                textInputUsername.error = getString(R.string.error_require_username)
                return@setOnClickListener
            } else {
                textInputUsername.error = null
            }
            if (textInputPassword.editText?.text.isNullOrBlank()) {
                textInputUsername.error = getString(R.string.error_require_password)
                return@setOnClickListener
            } else {
                textInputPassword.error = null
            }
            callLogin(textInputUsername.editText?.text.toString(), textInputPassword.editText?.text.toString())
        }
    }

    private fun callLogin(username: String, password: String) {

        val map = HashMap<String, String>()

        map["action"] = "login"
        map["username"] = username
        val encodedPassword = AppConstants.md5(password)
        if (encodedPassword.isBlank()) {
            return
        }
        map["password"] = encodedPassword
        map["registerId"] = FirebaseInstanceId.getInstance().token ?: ""
        NetworkConnection(this, LoginModel::class.qualifiedName.toString(), map, this, true).postRequest()

    }

    override fun onTaskDone(context: Context, data: DataModel?) {

        if (data != null) {
            val loginModel = data as LoginModel

            if (loginModel.message) {
                val preferences = AppSharedPreferences(this)
                with(preferences) {
                    loginModel.userdata?.let {
                        userId = it.id ?: ""
                        username = it.userName ?: ""
                        password = it.password ?: ""
                        firstName = it.firstName ?: ""
                        lastName = it.lastName ?: ""
                        adminType = it.adminType ?: ""
                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                        finish()
                    }
                }

            } else {

            }
        }
    }
}
