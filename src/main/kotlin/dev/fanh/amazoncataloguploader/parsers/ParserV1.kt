package dev.fanh.amazoncataloguploader.parsers

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
        TODO("Not yet implemented")
    }

}