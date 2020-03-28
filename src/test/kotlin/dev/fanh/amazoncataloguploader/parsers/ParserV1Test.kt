package dev.fanh.amazoncataloguploader.parsers

import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList
import org.junit.Test
import kotlin.test.assertEquals

class ParserV1Test {

    private val parser = ParserV1()

    @Test
    fun parseSpecies() {
        val result = parser.parseSpecies("testSpecies.json")
        val expectedSpecies = getExpectedSpecies()
        assertEquals(expectedSpecies, result, "Species object was not parsed properly")
    }

    @Test
    fun parseSpeciesList() {
        val result = parser.parseSpeciesList("testSpeciesList.json")
        val expectedSpecies = getExpectedSpeciesList()
        assertEquals(expectedSpecies, result, "Species object was not parsed properly")
    }

    @Test
    fun parseKingdoms() {
        val result = parser.parseKingdoms("testKingdoms.json")
        val expectedSpecies = getExpectedKingdoms()
        assertEquals(expectedSpecies, result, "Species object was not parsed properly")
    }

    private fun getExpectedSpecies(): Species {
        return Species()
    }

    private fun getExpectedSpeciesList(): SpeciesList {
        return SpeciesList()
    }

    private fun getExpectedKingdoms(): KingdomList {
        return KingdomList()
    }
}