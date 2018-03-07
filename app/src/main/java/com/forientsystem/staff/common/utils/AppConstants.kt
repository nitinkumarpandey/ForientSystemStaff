package com.forientsystem.staff.common.utils

import java.security.NoSuchAlgorithmException


/**
 * Created by nitin on 3/1/18.
 */
object AppConstants {
    const val DOMAIN = "http://www.adminmasters.com"
    const val URL = "${DOMAIN}/webservice/index.php?"

    fun md5(s: String): String {
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            messageDigest.forEach {
                hexString.append(Integer.toHexString(0xFF and it.toInt()))
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}