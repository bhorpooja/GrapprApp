package com.codekul.grapprapp.sharedprefs

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by pooja on 15/1/18.
 */
class Prefs {

    companion object {

        val PREFS_STORE_USER:String="user"
        val USER_ID:String="userId"
        val EMAIL_ID:String="emailId"
        val PASSWORD:String="password"
        val MOBILE_NO:String="mobileNo"
        val APP_ID:String="appId"

        fun saveUserData(context: Context, userId:String,
                         emailId:String, password:String,mobileNo: String){
            val sharePrefs:SharedPreferences=context.getSharedPreferences(PREFS_STORE_USER,
                    Context.MODE_PRIVATE)
            val editor=sharePrefs.edit()
            editor.putString(USER_ID,userId).apply()
            editor.putString(EMAIL_ID,emailId).apply()
            editor.putString(PASSWORD,password).apply()
            editor.putString(MOBILE_NO, mobileNo).apply()
        }

        fun saveAppId(context: Context,appId:String){
            val sharePrefs:SharedPreferences=context.getSharedPreferences(PREFS_STORE_USER,
                    Context.MODE_PRIVATE)
            val editor=sharePrefs.edit()
            editor.putString(APP_ID,appId).apply()
        }

        fun getUserId(context: Context):String{
            val sharePrefs:SharedPreferences=context.getSharedPreferences(PREFS_STORE_USER,
                    Context.MODE_PRIVATE)
            return sharePrefs.getString(USER_ID,null)
        }

        fun getMobileNo(context: Context):String{
            val sharePrefs:SharedPreferences=context.getSharedPreferences(PREFS_STORE_USER,
                    Context.MODE_PRIVATE)
            return sharePrefs.getString(MOBILE_NO,null)
        }

        fun getAppId(context: Context):String{
            val sharePrefs:SharedPreferences=context.getSharedPreferences(PREFS_STORE_USER,
                    Context.MODE_PRIVATE)
            return sharePrefs.getString(APP_ID,null)
        }

        fun clearUserData(context: Context){
            val sharePrefs:SharedPreferences=context.getSharedPreferences(PREFS_STORE_USER,
                    Context.MODE_PRIVATE)
            val editor=sharePrefs.edit()
            editor.clear()
            editor.apply()
        }
    }
}