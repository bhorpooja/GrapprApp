package com.codekul.grapprapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.codekul.grapprapp.domain.User
import com.codekul.grapprapp.domain.UserInfo
import com.codekul.grapprapp.rest.ApiService
import kotlinx.android.synthetic.main.activity_registration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    val TAG:String="@codekul"
    lateinit var edtName:EditText
    lateinit var edtEId:EditText
    lateinit var edtMobileNo:EditText
    lateinit var edtPswd:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        supportActionBar?.title = "User Registration"
//        var upArrow = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
//        upArrow.setColorFilter(resources.getColor(R.color.colorAccent),PorterDuff.Mode.SRC_ATOP)
//        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        edtName = findViewById(R.id.edtName)
        edtEId = findViewById(R.id.edtEId)
        edtMobileNo = findViewById(R.id.edtMobileNo)
        edtPswd = findViewById(R.id.edtPswd)

        btnRegister.setOnClickListener {
            validation()
        }
    }
        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            onBackPressed()
            return true
        }


    private fun validation() {
        if (edtName.text.isNullOrBlank() or
                edtEId.text.isNullOrBlank() or
                edtMobileNo.text.isNullOrBlank() or
                edtPswd.text.isNullOrBlank()){
            val simpleAlert= AlertDialog.Builder(this).create()
            simpleAlert.setTitle("User Registration")
            simpleAlert.setMessage("Mention all field details")
            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE,"ok",
                    { _, _ ->  })
            simpleAlert.show()
        }
        else{
            postUserData()
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }
    }

    private fun postUserData() {
        val apiService= ApiService.create()
        val call=apiService.registerUser(User(edtName.text.toString(),
                                                edtEId.text.toString(),
                                                edtMobileNo.text.toString(),
                                                edtPswd.text.toString()))
        call.enqueue(object : Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>?, response: Response<UserInfo>?) {

                val user=response?.body()
                if (response?.code()==200) {
                    Toast.makeText(applicationContext,"msg : "+user?.msg,Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "user : " + user?.result.toString())
                }
                else{
                    Toast.makeText(applicationContext,"Registration Unsuccessful....Try again....", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserInfo>?, t: Throwable?) {
                Log.i(TAG,"Error : "+t)
            }
        })
    }
}
