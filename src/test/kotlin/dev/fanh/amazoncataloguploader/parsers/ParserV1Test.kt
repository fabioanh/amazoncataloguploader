package dev.fanh.amazoncataloguploader.parsers

import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.testutils.*
import org.junit.Test
import kotlin.test.assertEquals

class ParserV1Test {

    private val parser = ParserV1()

    @Test
    fun parseBasicSpecies() {
        // given
        val expectedSpecies = getBasicSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        val result = parser.parseSpecies("./src/test/resources/testBasicSpecies.json", kingdom)
        // then
        assertEquals(expectedSpecies, result, "Basic Species object was not parsed properly")
    }

    @Test
    fun parseCompleteSpecies() {
        // given
        val expectedSpecies = getCompleteSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        val result = parser.parseSpecies("./src/test/resources/testCompleteSpecies.json", kingdom)
        // then
        assertEquals(expectedSpecies, result, "Full Species object was not parsed properly")
    }

    @Test
    fun parseSpeciesNoCommonNames() {
        // given
        val expectedSpecies = getNoCommonNamesSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        val result = parser.parseSpecies("./src/test/resources/testNoCommonNamesSpecies.json", kingdom)
        //then
        assertEquals(expectedSpecies, result, "Basic Species object was not parsed properly")
    }

    @Test
    fun parseSpeciesEmptyCommonNames() {
        // given
        val expectedSpecies = getEmptyCommonNamesSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        val result = parser.parseSpecies("./src/test/resources/testEmptyCommonNamesSpecies.json", kingdom)
        // then
        assertEquals(expectedSpecies, result, "Basic Species object was not parsed properly")
    }

    @Test
    fun parseSpeciesNoFullDescription() {
        // given
        val expectedSpecies = getNoFullDescriptionSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        val result = parser.parseSpecies("./src/test/resources/testNoFullDescriptionSpecies.json", kingdom)
        // then
        assertEquals(expectedSpecies, result, "Basic Species object was not parsed properly")
    }

    @Test
    fun parseSpeciesList() {
        // given
        val expectedSpecies = getExpectedSpeciesList()
        val kingdom = Kingdom("Animalia")
        // when
        val result = parser.parseSpeciesList("./src/test/resources/testSpeciesList.json", kingdom)
        // then
        assertEquals(expectedSpecies, result, "Species List object was not parsed properly")
    }

    @Test
    fun parseKingdoms() {
        // given
        val expectedSpecies = getExpectedKingdoms()
        // when
        val result = parser.parseKingdoms("./src/test/resources/testKingdoms.json")
        // then
        assertEquals(expectedSpecies, result, "Kingdoms object was not parsed properly")
    }

    @Test(expected = Exception::class)
    fun parseEmptyKingdoms() = parser.parseKingdoms("./src/test/resources/testEmptyKingdoms.json")


    private fun getExpectedSpeciesList(): SpeciesList {
        val animal1 = SpeciesListDataObject("idAnimal1",
                listOf(LanguagedValue("stripped jaguar", "en"),
                        LanguagedValue("jaguar rayado", "es"),
                        LanguagedValue("Jaguarayado", "unknown")),
                "scientificus nominus animalus 1")

        val animal2 = SpeciesListDataObject("idAnimal2",
                listOf(LanguagedValue("assassin ant", "en"),
                        LanguagedValue("Attentöterameise", "de"),
                        LanguagedValue("hormigota malota", "unknown"),
                        LanguagedValue("fourmi assassine", "fr"),
                        LanguagedValue("hormiga asesina", "es")),
                "scientificus nominus animalus 2")

        return SpeciesList("V1", "animalia", listOf(animal1, animal2))
    }

    private fun getExpectedKingdoms(): KingdomList = KingdomList("V1", listOf(Kingdom("Animalia")))
}