package com.learn.look.Utils.SharedPreference

import android.content.Context
import android.content.SharedPreferences

class SharedPrefLogin(var context: Context) {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun provideSharedPreference(): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    companion object {
        const val PREF_NAME = "SharedPreferenceLogin"
        const val PRIVATE_MODE = 0

        //User Data
        const val IS_LOGGED_IN = "isLoggedIn"
        const val USER_TOKEN = "userToken"
        const val USER_NAME = "userName"
        const val USER_EMAIL = "userEmail"
        const val USER_PHOTO = "userPhoto"
    }

    fun saveLoginCredentials() {

    }


}