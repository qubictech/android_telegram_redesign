package com.tarms.bd.messagingapp.utils

import android.content.Context

class PrefUtils {
    companion object {
        const val USER_NAME = "user-name"
        const val USER_AVATAR_URL = "user-avatar-url"

        const val SETTINGS_PREF = "com.tarms.messagingapp.setting.pref"

    }

    fun getSettingPref(context: Context, key: String?): Boolean {
        val preferences = context.getSharedPreferences(SETTINGS_PREF, Context.MODE_PRIVATE)

        return preferences.getBoolean(key!!, true)
    }

    fun setSettingPref(context: Context, key: String?, value: Boolean?) {
        val preferences = context.getSharedPreferences(SETTINGS_PREF, Context.MODE_PRIVATE).edit()

        preferences.putBoolean(key!!, value!!).apply()
    }
}