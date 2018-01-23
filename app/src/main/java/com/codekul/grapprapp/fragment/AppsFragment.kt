package com.codekul.grapprapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.codekul.grapprapp.R
import com.codekul.grapprapp.domain.AppInfo
import com.codekul.grapprapp.domain.App
import com.codekul.grapprapp.rest.ApiService
import com.codekul.grapprapp.sharedprefs.Prefs
import retrofit2.Callback
import retrofit2.Response


class AppsFragment : Fragment() {

    val TAG:String="@codekul"
    var al_category = ArrayList<App>()
    var int_position: Int = 0
    lateinit var imgAppIcon: ImageView
    lateinit var txtAppName: TextView
    lateinit var txtAppInstall: TextView
    lateinit var txtAppOffers: TextView
    lateinit var btnInstall: Button
    lateinit var view12: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (arguments != null) {
//        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view12 = inflater!!.inflate(R.layout.fragment_apps, container, false)
        return view12
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.title = "Apps"
        arrayList()
    }

    private fun arrayList() {
        val apiService = ApiService.create()
        val call = apiService.getApps()
        Log.i(TAG,"URL : "+call.request().url())
        call.enqueue(object : Callback<AppInfo> {

            override fun onResponse(call: retrofit2.Call<AppInfo>?, response: Response<AppInfo>?) {
                val list = response?.body()
                Log.i(TAG, "Response:" + list)
                Log.i(TAG, "Size:" + list?.result?.size)

                list?.result?.forEach {
                    app -> App(app.id, app.appName, app.url, app.offer, app.category, app.date)
                    al_category.add(app)
                }
                setData()
            }
            override fun onFailure(call: retrofit2.Call<AppInfo>?, t: Throwable?) {
                Log.i(TAG, "ErrorM: " + t?.message)
                Log.i(TAG, "ErrorC: " + t?.cause)
                Log.i(TAG, "ErrorL: " + t?.localizedMessage)
            }
        })
    }

    fun setData() {

        imgAppIcon = view12.findViewById<ImageView>(R.id.imgAppIcon)
        txtAppName = view12.findViewById<TextView>(R.id.txtAppName)
        txtAppInstall = view12.findViewById<TextView>(R.id.txtAppInstall)
        txtAppOffers = view12.findViewById<TextView>(R.id.txtAppOffer)
        btnInstall = view12.findViewById<Button>(R.id.btnInstall)

        int_position = arguments.getInt("position")
        txtAppName.text = al_category.get(int_position).appName
        txtAppOffers.text = al_category.get(int_position).offer.toString()

        val appId=al_category.get(int_position).id
        Prefs.saveAppId(context,appId)

        btnInstall.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW)
            webIntent.data = Uri.parse(al_category.get(int_position).url)
            startActivity(webIntent)
            LocalBroadcastManager.getInstance(context).sendBroadcast(webIntent)
        }
    }


//    companion object {
//
//        private val ARG_PARAM1 = "param1"
//
//        fun newInstance(param1:Int): AppsFragment {
//            val fragment = AppsFragment()
//            val args = Bundle()
//            args.putInt(ARG_PARAM1, param1)
//            fragment.arguments = args
//            return fragment
//        }

//    }


}