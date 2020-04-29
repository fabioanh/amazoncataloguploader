package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.services.s3.AmazonS3
import com.google.gson.Gson
import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.parsers.ParserVersion
import dev.fanh.amazoncataloguploader.testutils.getBasicSpecies
import dev.fanh.amazoncataloguploader.testutils.getBasicSpeciesAnt
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class S3UploadClientTest {

    @MockK
    private lateinit var s3Client: AmazonS3

    @InjectMockKs
    private var client = S3UploadClient()

    private val gson = Gson()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun getType() {
        // when
        val client = S3UploadClient()
        // then
        assertEquals(UploadClientType.S3, client.type, "Wrong type given to upload client")
    }

    @Test
    fun uploadSingleKingdom() {
        // given
        val kingdomList = getSingleKingdom()
        // when
        client.uploadKingdoms(kingdomList)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/kingdoms.json", gson.toJson(kingdomList)) }
    }

    @Test
    fun uploadMultipleKingdoms(){
        // given
        val kingdomList = getKingdoms()
        // when
        client.uploadKingdoms(kingdomList)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/kingdoms.json", gson.toJson(kingdomList)) }
    }

    @Test
    fun uploadSpeciesList() {
        // given
        val kingdom = Kingdom("Animalia")
        val speciesList = getSpiecesList()
        // when
        client.uploadSpeciesList(speciesList, kingdom)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.toLowerCase()}/speciesList.json", gson.toJson(speciesList)) }
    }

    @Test
    fun uploadSingleSpecies(){
        // given
        val species = getBasicSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        client.uploadSpecies(listOf(species), kingdom)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.toLowerCase()}/species/${species.id}.json", gson.toJson(species)) }
    }

    @Test
    fun uploadMultipleSpecies(){
        // given
        val jaguar = getBasicSpecies()
        val ant = getBasicSpeciesAnt()
        val kingdom = Kingdom("Animalia")
        // when
        client.uploadSpecies(listOf(jaguar, ant), kingdom)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.toLowerCase()}/species/${jaguar.id}.json", gson.toJson(jaguar)) }
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.toLowerCase()}/species/${ant.id}.json", gson.toJson(ant)) }
    }

    private fun getSpiecesList(): SpeciesList {
        val speciesList = SpeciesListDataObject("id", listOf(LanguagedValue("a name", "en")), "A Scientific Name")
        return SpeciesList(ParserVersion.V1.name, "animalia", listOf(speciesList))
    }

    private fun getSingleKingdom(): KingdomList {
        return KingdomList(ParserVersion.V1.name, listOf(Kingdom("Animalia")))
    }

    private fun getKingdoms(): KingdomList {
        return KingdomList(ParserVersion.V1.name, listOf(Kingdom("Animalia"), Kingdom("Herbalia")))
    }
}