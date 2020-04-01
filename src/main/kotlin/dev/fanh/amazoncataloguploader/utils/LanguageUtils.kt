package dev.fanh.amazoncataloguploader.utils

import com.google.gson.JsonElement
import com.google.gson.JsonNull

private val languagesMap = mapOf<String?, String>(
        "Alemán" to "de",
        "Búlgaro" to "bg",
        "Checo" to "cs",
        "Chimane" to "cas",
        "Chimani" to "cas",
        "Danés" to "da",
        "Danish" to "da",
        "Dutch; Flemish" to "nl",
        "Embera" to "embera",
        "Esloveno" to "sl",
        "Español" to "es",
        "Estonian" to "et",
        "Finlandés" to "fi",
        "Finnish" to "fi",
        "Francés" to "fr",
        "French" to "fr",
        "Galés" to "cy",
        "German" to "de",
        "Guahibo" to "guh",
        "Guaraní" to "gn",
        "Guarani" to "gn",
        "Guarayo" to "gyr",
        "Holandés" to "nl",
        "Húngaro" to "hu",
        "Inglés" to "en",
        "Italiano" to "it",
        "indígena" to "indigenous",
        "Kwiba" to "kwiba",
        "Kubeo" to "kubeo",
        "Lengua indígena" to "indigenous",
        "Lituano" to "lt",
        "Noruego" to "no",
        "Noruego Bokmål" to "nb",
        "Noruego Nynorsk" to "nn",
        "Okaima" to "okaima",
        "Piaroa" to "pid",
        "Polaco" to "pl",
        "Polish" to "pl",
        "Portugués" to "pt",
        "Portuguese" to "pt",
        "Russian" to "ru",
        "Sueco" to "sv",
        "Swedish" to "sv",
        "Tukano" to "tuo",
        "Tupí" to "tupi",
        "Turco" to "tr",
        "Yuri" to "Yuri",

        null to "unknown")

public fun languageFromJsonV1(languageRawString: JsonElement?): String {
    if (languageRawString is JsonNull) {
        return "unknown"
    }

    return languagesMap[languageRawString?.asString?.trim()]
            ?: run{println("Language ${languageRawString?.asString} not supported"); "unknown"}

}