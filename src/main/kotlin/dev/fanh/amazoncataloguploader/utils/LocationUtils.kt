package dev.fanh.amazoncataloguploader.utils

import com.google.gson.JsonElement

private val locationsMap = mapOf<String?, String>("Colombia" to "co", "Global" to "Global")

fun isoLocation(location:String): String =  locationsMap[location] ?: location
