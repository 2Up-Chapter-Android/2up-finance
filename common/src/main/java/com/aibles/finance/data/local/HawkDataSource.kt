package com.aibles.finance.data.local

import com.orhanobut.hawk.Hawk

object HawkDataSource {
    fun getAccessToken(): String = Hawk.get(HawkKey.ACCESS_TOKEN, "null")

    fun getRefreshToken(): String = Hawk.get(HawkKey.REFRESH_TOKEN, "null")

    fun saveAccessToken(token: String)  {
        Hawk.put(HawkKey.ACCESS_TOKEN, token)
    }

    fun saveRefreshToken(token: String)  {
        Hawk.put(HawkKey.REFRESH_TOKEN, token)
    }

    object HawkKey{
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}