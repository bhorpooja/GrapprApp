package com.codekul.grapprapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.codekul.grapprapp.domain.Credential
import com.codekul.grapprapp.domain.LoginInfo
import com.codekul.grapprapp.rest.ApiService
import com.codekul.grapprapp.sharedprefs.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val TAG:String="@codekul"
    lateinit var edtEmailId:EditText
    lateinit var edtPassword:EditText
    lateinit var txtSignUp:TextView
    lateinit var txtForgetPwd:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        if (Prefs.getUserId(this)!=null){
//            startActivity(Intent(this,MainActivity::class.java)
//                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
//        }
        supportActionBar?.title="CashBolo Login"

        edtEmailId=findViewById(R.id.edtEmailId)
        edtPassword=findViewById(R.id.edtPassword)
        txtSignUp=findViewById(R.id.txtSignUp)
        txtForgetPwd=findViewById(R.id.txtSignUp)

        btnLogin.setOnClickListener {
            validation()
        }

        txtSignUp.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }

    }

    private fun validation() {

        if(edtEmailId.text.isNullOrBlank() or
                edtPassword.text.isNullOrBlank()){

            val simpleAlert=AlertDialog.Builder(this).create()
            simpleAlert.setTitle("User Login")
            simpleAlert.setMessage("Mention all details")
            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE,"ok",
                    { _, _ ->  })
            simpleAlert.show()
        }
        else{
            loginUser()
            val intent=Intent(applicationContext,MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val apiService=ApiService.create()
        val call=apiService.userLogin(Credential(edtEmailId.text.toString(),
                                                 edtPassword.text.toString()))
        Log.i(TAG,"URL : "+call.request().url())
        call.enqueue(object : Callback<LoginInfo> {

            override fun onResponse(call: Call<LoginInfo>?, response: Response<LoginInfo>?) {

                val user=response?.body()
                if (response?.code()==200) {
                    Prefs.saveUserData(applicationContext, user?.result as String,
                            edtEmailId.text.toString(), edtPassword.text.toString(),user.mobileNo)
                    Toast.makeText(applicationContext,"Login Successfully..!!",Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "userId : " + Prefs.getUserId(applicationContext))

                }
                else{
                    Toast.makeText(applicationContext,"Login Unsuccessful....Try again....",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginInfo>?, t: Throwable?) {
                Log.i(TAG,"Error : "+t)
            }
        })
    }
}
