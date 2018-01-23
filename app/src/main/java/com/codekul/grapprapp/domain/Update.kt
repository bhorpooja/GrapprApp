package com.codekul.grapprapp.domain

/**
 * Created by pooja on 22/1/18.
 */
data class Update (val userId:String,
                   val appId:String,
                   val installed:Boolean,
                   val uninstalled:Boolean,
                   val skipped:Boolean)