package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.google.gson.Gson
import dev.fanh.amazoncataloguploader.data.Kingdom
import dev.fanh.amazoncataloguploader.testutils.getBasicSpecies
import dev.fanh.amazoncataloguploader.testutils.getBasicSpeciesAnt
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DynamoDBUploadClientTest {

    @MockK(relaxed = true)
    private lateinit var dynamoDB: DynamoDB

    @InjectMockKs
    private lateinit var client: DynamoDBUploadClient

    private val gson = Gson()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.client = DynamoDBUploadClient(dynamoDB)
    }

    @Test
    fun getType() {
        // when
        val client = DynamoDBUploadClient()
        // then
        assertEquals(UploadClientType.DYNAMO_DB, client.type, "Wrong type given to upload client")
    }

    @Test
    fun uploadSingleKingdom() {
        // given
        val kingdomList = TestHelper.getSingleKingdom()
        val singleKingdomItem = Item()
        val table = dynamoDB.getTable("kingdom")
        singleKingdomItem
            .withPrimaryKey("name", kingdomList.kingdoms[0].name)
            .withString("version", kingdomList.version)
        // when
        client.uploadKingdoms(kingdomList)
        // then
        verify { dynamoDB.getTable("kingdom") }
        verify(exactly = 1) { table.putItem(singleKingdomItem) }
    }

    @Test
    fun uploadMultipleKingdoms() {
        // given
        val kingdomList = TestHelper.getKingdoms()
        val table = dynamoDB.getTable("kingdom")
        // when
        client.uploadKingdoms(kingdomList)
        // then
        verify { dynamoDB.getTable("kingdom") }
        verify(exactly = kingdomList.kingdoms.size) { table.putItem(any<Item>()) }
    }

    @Test
    fun uploadSpeciesSummaryList() {
        // given
        val kingdom = Kingdom("Animalia")
        val speciesList = TestHelper.getSpeciesList()
        val table = dynamoDB.getTable("speciesSummary")
        // when
        client.uploadSpeciesList(speciesList, kingdom)
        // then
        verify { dynamoDB.getTable("speciesSummary") }
        verify(exactly = speciesList.species.size) { table.putItem(any<Item>()) }
    }

    @Test
    fun uploadSingleSpecies() {
        // given
        val species = getBasicSpecies()
        val kingdom = Kingdom("Animalia")
        val table = dynamoDB.getTable("species")
        val singleSpeciesItem = Item()
            .withPrimaryKey("id", species.id)
            .withString("description", species.description)
            .withString("fullDescription", species.fullDescription)
            .withString("kingdom", species.kingdom)
            .withString("scientificName", species.scientificName)
            .withString("version", species.version)
            .withMap("commonNames", species.commonNames?.groupBy({ it.isoLanguage }, { it.value }))
        // when
        client.uploadSpecies(listOf(species), kingdom)
        // then
        verify { dynamoDB.getTable("species") }
        verify(exactly = 1) { table.putItem(singleSpeciesItem) }
    }

    @Test
    fun uploadMultipleSpecies() {
        // given
        val jaguar = getBasicSpecies()
        val ant = getBasicSpeciesAnt()
        val kingdom = Kingdom("Animalia")
        val table = dynamoDB.getTable("species")
        // when
        client.uploadSpecies(listOf(jaguar, ant), kingdom)
        // then
        verify { dynamoDB.getTable("species") }
        verify(exactly = 2) { table.putItem(any<Item>()) }
    }
}