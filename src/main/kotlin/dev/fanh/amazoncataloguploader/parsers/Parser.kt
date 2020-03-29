package dev.fanh.amazoncataloguploader.parsers

import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList
import java.io.File
import java.io.Reader

interface Parser {
    val version: ParserVersion

    /**
     * Parse method to convert a single species data to the application required structure
     */
    fun parseSpecies(fileName: String): Species

    /**
     * Parse method to convert the list of species data to the application required list structure
     */
    fun parseSpeciesList(fileName: String): SpeciesList

    /**
     * Parse method to convert the given list of kingdoms available to the application required structure
     */
    fun parseKingdoms(fileName: String): KingdomList
}

fun jsonFileReader(fileName: String): Reader{
    return File(fileName).bufferedReader(Charsets.UTF_8)
}
