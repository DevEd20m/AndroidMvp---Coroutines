package com.deved.coroutinesmvp.data.models

data class ErrorBody(
    val name: String?,
    val status: Int?,
    val type: Int?,
    val id: Int?,
    val message: String?
)