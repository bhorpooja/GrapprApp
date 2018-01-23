package com.codekul.grapprapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.codekul.grapprapp.domain.Update
import com.codekul.grapprapp.domain.UserInfo
import com.codekul.grapprapp.rest.ApiService
import com.codekul.grapprapp.sharedprefs.Prefs
import retrofit2.Callback
import retrofit2.Response

open class MyReceiver : BroadcastReceiver() {

    val TAG:String="@codekul"
    lateinit var userId:String
    lateinit var appId:String

    override fun onReceive(context: Context, intent: Intent) {

        userId=Prefs.getUserId(context)
        appId=Prefs.getAppId(context)
        if (intent.action.equals(Intent.ACTION_PACKAGE_ADDED)) {
            Log.i(TAG, "App Installed.... ")
            getInstallApp()
        }
        else if(intent.action.equals(Intent.ACTION_PACKAGE_REMOVED)){
            Log.i(TAG,"App Uninstalled....")
            getUninstallApp()
        }
        else{
            Log.i(TAG,"App Skipped...")
            getSkipApp()
        }
    }

    private fun getInstallApp(){

            val user=Update(userId,appId,true,false,false)
            val apiService = ApiService.create()
            val call = apiService.updateUser(user)
            Log.i(TAG,"URL : "+call.request().url())
            call.enqueue(object : Callback<UserInfo> {

                override fun onResponse(call: retrofit2.Call<UserInfo>?, response: Response<UserInfo>?) {
                    val list = response?.body()
                    Log.i(TAG,"msg : "+list?.msg)
                    Log.i(TAG,"User : "+list?.result)
                }

                override fun onFailure(call: retrofit2.Call<UserInfo>?, t: Throwable?) {
                    Log.i(TAG, "ErrorM: " + t?.message)
                    Log.i(TAG, "ErrorC: " + t?.cause)
                    Log.i(TAG, "ErrorL: " + t?.localizedMessage)
                }
            })
    }
    private fun getUninstallApp(){

        val user=Update(userId,appId,false,true,false)
        val apiService = ApiService.create()
        val call = apiService.updateUser(user)
        Log.i(TAG,"URL : "+call.request().url())
        call.enqueue(object : Callback<UserInfo> {

            override fun onResponse(call: retrofit2.Call<UserInfo>?, response: Response<UserInfo>?) {
                val list = response?.body()
                Log.i(TAG,"msg : "+list?.msg)
                Log.i(TAG,"User : "+list?.result)
            }

            override fun onFailure(call: retrofit2.Call<UserInfo>?, t: Throwable?) {
                Log.i(TAG, "ErrorM: " + t?.message)
                Log.i(TAG, "ErrorC: " + t?.cause)
                Log.i(TAG, "ErrorL: " + t?.localizedMessage)
            }
        })
    }

    private fun getSkipApp(){

        val user=Update(userId,appId,false,false,true)
        val apiService = ApiService.create()
        val call = apiService.updateUser(user)
        Log.i(TAG,"URL : "+call.request().url())
        call.enqueue(object : Callback<UserInfo> {

            override fun onResponse(call: retrofit2.Call<UserInfo>?, response: Response<UserInfo>?) {
                val list = response?.body()
                Log.i(TAG,"msg : "+list?.msg)
                Log.i(TAG,"User : "+list?.result)
            }

            override fun onFailure(call: retrofit2.Call<UserInfo>?, t: Throwable?) {
                Log.i(TAG, "ErrorM: " + t?.message)
                Log.i(TAG, "ErrorC: " + t?.cause)
                Log.i(TAG, "ErrorL: " + t?.localizedMessage)
            }
        })
    }
}
