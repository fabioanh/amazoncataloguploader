package dev.fanh.amazoncataloguploader.parsers

interface Parser {
    val version:ParserVersion

    /**
     * Parse method to convert to the application supported JSON files
     */
    fun parse(filename: String)
}
