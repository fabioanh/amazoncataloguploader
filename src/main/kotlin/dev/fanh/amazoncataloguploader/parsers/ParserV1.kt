package dev.fanh.amazoncataloguploader.parsers

import com.google.gson.Gson
import com.google.gson.JsonObject
import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList

public class ParserV1 : Parser {
    override val version: ParserVersion = ParserVersion.V1

    override fun parseSpecies(filename: String): Species {
        TODO("Not yet implemented")
    }

    override fun parseSpeciesList(filename: String): SpeciesList {
        TODO("Not yet implemented")
    }

    override fun parseKingdoms(filename: String): KingdomList {
        val kingdoms = Gson().fromJson(jsonFileReader(filename), Array<String>::class.java)
        if (kingdoms.isEmpty()){
            throw Exception("The list of kingdoms cannot be empty")
        }
        return KingdomList(this.version.name, kingdoms.asList())
    }

}