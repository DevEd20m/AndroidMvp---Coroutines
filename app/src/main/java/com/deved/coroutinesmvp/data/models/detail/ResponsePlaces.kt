package com.deved.coroutinesmvp.data.models.detail

data class ResponsePlaces(val response: String?, val content: ContentResponsePlaces?)
data class ContentResponsePlaces(val places: List<ContentResponsePlace>?)
data class ContentResponsePlace(
    val nameTourist: String?,
    val description: String?,
    val image: String?
)
