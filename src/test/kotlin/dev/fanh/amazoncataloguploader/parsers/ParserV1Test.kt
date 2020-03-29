package dev.fanh.amazoncataloguploader.parsers

import dev.fanh.amazoncataloguploader.data.*
import org.junit.Test
import kotlin.test.assertEquals

class ParserV1Test {

    private val parser = ParserV1()

    @Test
    fun parseMinimumSpecies() {
        val result = parser.parseSpecies("testSpecies.json")
        val expectedSpecies = getExpectedSpecies()
        assertEquals(expectedSpecies, result, "Species object was not parsed properly")
    }

    @Test
    fun parseCompleteSpecies() {
        val result = parser.parseSpecies("testCompleteSpecies.json")
        val expectedSpecies = getExpectedCompleteSpecies()
        assertEquals(expectedSpecies, result, "Full Species object was not parsed properly")
    }

    @Test
    fun parseSpeciesList() {
        val result = parser.parseSpeciesList("./src/test/resources/testSpeciesList.json")
        val expectedSpecies = getExpectedSpeciesList()
        assertEquals(expectedSpecies, result, "Species List object was not parsed properly")
    }

    @Test
    fun parseKingdoms() {
        val result = parser.parseKingdoms("./src/test/resources/testKingdoms.json")
        val expectedSpecies = getExpectedKingdoms()
        assertEquals(expectedSpecies, result, "Kingdoms object was not parsed properly")
    }

    @Test(expected = Exception::class)
    fun parseEmptyKingdoms() {
        val result = parser.parseKingdoms("./src/test/resources/testEmptyKingdoms.json")
    }

    private fun getExpectedSpecies(): Species {
        return Species(version = "V1",
                id = "idAnimal1",
                commonNames = listOf(LanguagedValue("stripped jaguar", "en")),
                description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
                fullDescription = "The stripped jaguar is very difficult to spot as it hides in the middle of Zebra groups. Some tribes refer to it as a wolf in sheep's clothing",
                scientificName = "Jaguarus Felinus Rayadus")
    }

    private fun getExpectedCompleteSpecies(): Species {
        return Species(version = "V1",
                id = "idAnimal1",
                commonNames = listOf(LanguagedValue("stripped jaguar", "en")),
                description = "The stripped jaguar is a cat that resembles a Zebra, beware!",
                fullDescription = "The stripped jaguar is very difficult to spot as it hides in the middle of Zebra groups. Some tribes refer to it as a wolf in sheep's clothing",
                scientificName = "Jaguarus Felinus Rayadus",
                behaviour = "This animal behaves wildly",
                endangeredStatus = listOf(LocalisedValue("En Peligro", "co")),
                feeding = "Eats only aquatic zebras from the Peruvian Amazon",
                habitat = "Amazon river bank",
                imageURLs = listOf("https://d2ouvy59p0dg6k.cloudfront.net/img/original/original_ww2137556.jpg"),
                lifecycle = "Its reproduction cycle is tied to the pink dolphins dancing habits",
                lifeForm = "It's mostly active during the evenings",
                migration = "Doesn't present migratory habits as it can eat aquatic zebras all year long",
                reproduction = "Creates nests on top of the tallest Ceibas")
    }

    private fun getExpectedSpeciesList(): SpeciesList {
        val animal1 = SpeciesListDataObject("idAnimal1",
                listOf(LanguagedValue("stripped jaguar", "en"),
                        LanguagedValue("jaguar rayado", "es"),
                        LanguagedValue("Jaguarayado", "unknown")))

        val animal2 = SpeciesListDataObject("idAnimal2",
                listOf(LanguagedValue("assassin ant", "en"),
                        LanguagedValue("Attentöterameise", "de"),
                        LanguagedValue("hormigota malota", "unknown"),
                        LanguagedValue("fourmi assassine", "fr"),
                        LanguagedValue("hormiga asesina", "es")))

        return SpeciesList("V1", listOf(animal1, animal2))
    }

    private fun getExpectedKingdoms(): KingdomList {
        return KingdomList("V1", listOf("Animalia"))
    }
}