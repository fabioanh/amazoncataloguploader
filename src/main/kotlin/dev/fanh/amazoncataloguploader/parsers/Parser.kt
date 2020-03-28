package dev.fanh.amazoncataloguploader.parsers

import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList

interface Parser {
    val version: ParserVersion

    /**
     * Parse method to convert a single species data to the application required structure
     */
    fun parseSpecies(filename: String): Species

    /**
     * Parse method to convert the list of species data to the application required list structure
     */
    fun parseSpeciesList(filename: String): SpeciesList

    /**
     * Parse method to convert the given list of kingdoms available to the application required structure
     */
    fun parseKingdoms(filename: String): KingdomList


}
