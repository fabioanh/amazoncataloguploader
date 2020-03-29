package dev.fanh.amazoncataloguploader.parsers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.utils.languageFromJsonV1

public class ParserV1 : Parser {
    override val version: ParserVersion = ParserVersion.V1
    private val gson: Gson = GsonBuilder().serializeNulls().create()

    override fun parseSpecies(filename: String): Species {
        TODO("Not yet implemented")
    }

    override fun parseSpeciesList(filename: String): SpeciesList {
        val speciesArrayJsonObject = gson.fromJson(jsonFileReader(filename), Array<JsonObject>::class.java)
        val speciesDataObjects = ArrayList<SpeciesListDataObject>()
        return SpeciesList(this.version.name,
                speciesArrayJsonObject.map { obj -> SpeciesListDataObject(obj.get("_id").asString, extractCommonNames(obj.get("commonNames"))) })
    }

    private fun extractCommonNames(jsonCommonNames: JsonElement): List<LanguagedValue> {
        return jsonCommonNames.asJsonArray.map { obj -> LanguagedValue(obj.asJsonObject.get("name").asString, languageFromJsonV1(obj.asJsonObject.get("language"))) }
    }

    override fun parseKingdoms(filename: String): KingdomList {
        val kingdoms = gson.fromJson(jsonFileReader(filename), Array<String>::class.java)
        if (kingdoms.isEmpty()) {
            throw Exception("The list of kingdoms cannot be empty")
        }
        return KingdomList(this.version.name, kingdoms.asList())
    }

}