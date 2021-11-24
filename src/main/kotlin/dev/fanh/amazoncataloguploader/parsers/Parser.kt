package dev.fanh.amazoncataloguploader.parsers

import dev.fanh.amazoncataloguploader.data.Kingdom
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
    fun parseSpecies(filename: String, kingdom: Kingdom): Species

    /**
     * Parse method to convert the list of species data to the application required list structure
     */
    fun parseSpeciesList(filename: String, kingdom: Kingdom): SpeciesList

    /**
     * Parse method to convert the given list of kingdoms available to the application required structure
     */
    fun parseKingdoms(filename: String): KingdomList
}

fun jsonFileReader(filename: String): Reader{
    return File(filename).bufferedReader(Charsets.UTF_8)
}
