package com.atarious.userdetails.Model

import org.json.JSONArray
import org.json.JSONObject
import java.util.*

data class User (
    val id:Number,
    val name:String,
    val username:String,
    val email:String,
    val address:Data,
    val phone:String,
    val website:String,
    val company:CompanyData
)
data class Data(
    val street:String,
    val suite:String,
    val city:String,
    val zipcode:String
)

data class  CompanyData(
    val name:String
)