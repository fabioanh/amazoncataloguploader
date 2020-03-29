package dev.fanh.amazoncataloguploader.utils

import com.google.gson.JsonElement

private val locationsMap = mapOf<String?, String>("Colombia" to "co", "Global" to "Global")

public fun isoLocation(location:String): String {
    return locationsMap[location]
            ?: location
}