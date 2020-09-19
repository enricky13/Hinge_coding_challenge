package com.example.hinge_coding_challenge.entity

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

class SharedPrefManager {
    var preferences: SharedPreferences? = null

    fun with(application: Application) {
        preferences = application.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    fun put(customerData: CustomerData?, key: String) {
        val jsonString = GsonBuilder().create().toJson(customerData)
        preferences?.edit()?.putString(key, jsonString)?.apply()
    }

    fun get(key: String): CustomerData {
        val value = preferences?.getString(key, null)
        return GsonBuilder().create().fromJson(value, CustomerData::class.java)
    }

    companion object {
        const val SHARED_PREF_NAME = "SHARED PREF FILES"
    }
}