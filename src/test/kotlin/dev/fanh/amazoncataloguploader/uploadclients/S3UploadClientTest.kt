package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.services.s3.AmazonS3
import com.google.gson.Gson
import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.testutils.getBasicSpecies
import dev.fanh.amazoncataloguploader.testutils.getBasicSpeciesAnt
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class S3UploadClientTest {

    @MockK(relaxed = true)
    private lateinit var s3Client: AmazonS3

    @InjectMockKs
    private lateinit var client:S3UploadClient

    private val gson = Gson()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        this.client = S3UploadClient(s3Client)
    }

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
        val kingdomList = TestHelper.getSingleKingdom()
        // when
        client.uploadKingdoms(kingdomList)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/kingdoms.json", gson.toJson(kingdomList)) }
    }

    @Test
    fun uploadMultipleKingdoms(){
        // given
        val kingdomList = TestHelper.getKingdoms()
        // when
        client.uploadKingdoms(kingdomList)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/kingdoms.json", gson.toJson(kingdomList)) }
    }

    @Test
    fun uploadSpeciesList() {
        // given
        val kingdom = Kingdom("Animalia")
        val speciesList = TestHelper.getSpeciesList()
        // when
        client.uploadSpeciesList(speciesList, kingdom)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.lowercase()}/speciesList.json", gson.toJson(speciesList)) }
    }

    @Test
    fun uploadSingleSpecies(){
        // given
        val species = getBasicSpecies()
        val kingdom = Kingdom("Animalia")
        // when
        client.uploadSpecies(listOf(species), kingdom)
        // then
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.lowercase()}/species/${species.id}.json", gson.toJson(species)) }
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
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.lowercase()}/species/${jaguar.id}.json", gson.toJson(jaguar)) }
        verify { s3Client.putObject("amazoncatalogfanh", "/${kingdom.name.lowercase()}/species/${ant.id}.json", gson.toJson(ant)) }
    }


}