package com.forientsystem.staff.fcm


import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        sendRegistrationToServer(FirebaseInstanceId.getInstance().token)
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        println("FCM Token ====> " + token!!)
    }
}
