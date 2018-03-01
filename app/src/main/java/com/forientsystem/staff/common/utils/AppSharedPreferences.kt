package com.forientsystem.staff.common.utils

import android.content.Context
import com.forientsystem.staff.common.extensions.DelegatesExt

/**
 * Created by nitin on 2/28/18.
 */
class AppSharedPreferences(context: Context) {


    var username by DelegatesExt.preference(context, "username", "")
    var password by DelegatesExt.preference(context, "password", "")
    var userId by DelegatesExt.preference(context, "user_id", "")
    var adminType by DelegatesExt.preference(context, "admin_type", "")
    var firstName by DelegatesExt.preference(context, "first_name", "")
    var lastName by DelegatesExt.preference(context, "last_name", "")

}