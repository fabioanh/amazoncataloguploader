package dev.fanh.amazoncataloguploader.utils

import com.google.gson.JsonElement

private val languagesMap = mapOf<String?, String>("Español" to "es", "Alemán" to "de", "Francés" to "fr", "Inglés" to "en", null to "unknown")

public fun languageFromJsonV1(languageRawString: JsonElement?): String {
    return languagesMap[languageRawString?.asString]
            ?: throw Exception("Language ${languageRawString?.asString} not supported")
}