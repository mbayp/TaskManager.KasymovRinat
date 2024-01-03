package com.example.taskmanager.data.local

import android.content.Context

class Pref(context: Context) {
    private val pref=context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isBoardingShowed(): Boolean{
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onBoardingShow(){
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

       fun saveName(name: String){
           pref.edit().putString(NAME_KEY, name).apply()
       }

    fun getName(): String?{
        return pref.getString(NAME_KEY, null)
    }
    companion object{
        const val PREF_NAME="pref.name"
        const val SHOWED_KEY="showed.key"
        const val NAME_KEY="name.key"
    }
}