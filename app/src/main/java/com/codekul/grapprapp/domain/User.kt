package com.codekul.grapprapp.domain

/**
 * Created by pooja on 15/1/18.
 */
 class User(){

    lateinit var name:String
    lateinit var emailId:String
    lateinit var mobileNo:String
    lateinit var password:String
    lateinit var installedApps:List<String>
    lateinit var uninstalledApps:List<String>
    lateinit var skippedApps:List<String>

    constructor(name:String,emailId:String,mobileNo:String,password:String):this(){
        this.name=name
        this.emailId=emailId
        this.mobileNo=mobileNo
        this.password=password
    }
}