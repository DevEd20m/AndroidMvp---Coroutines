package com.deved.coroutinesmvp.models

data class RequestLogin(val email: String?, val password: String?)
data class ResponseLogin(val response: String?, val content: ContenResponseLogin?)
data class ContenResponseLogin(val result:String?)