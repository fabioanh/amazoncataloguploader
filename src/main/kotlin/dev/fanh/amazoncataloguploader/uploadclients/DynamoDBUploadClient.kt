package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.google.gson.Gson
import dev.fanh.amazoncataloguploader.data.Kingdom
import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}

class DynamoDBUploadClient : UploadClient {

    override val type: UploadClientType = UploadClientType.DYNAMO_DB

    private lateinit var dynamoClient: AmazonDynamoDB

    private val credentials: AWSCredentials = run {
        val props = javaClass.classLoader.getResourceAsStream("aws-credentials.properties").use {
            Properties().apply { load(it) }
        }
        BasicAWSCredentials(props["accessKey"] as String?, props["secretKey"] as String?)
    }

    private val gson = Gson()

    private val kingdomsTable = "Kingdoms"
    private val speciesListTable = "SpeciesList"
    private val speciesTable = "Species"

    constructor() {
        dynamoClient = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.SA_EAST_1)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .build()
    }

    constructor(dynamoClient: AmazonDynamoDB) {
        this.dynamoClient = dynamoClient
    }

    /**
     * Upload a list of species
     */
    override fun uploadSpecies(species: List<Species>, kingdom: Kingdom) {
        TODO("Not yet implemented")
    }

    /**
     * Upload the SpeciesList information
     */
    override fun uploadSpeciesList(speciesList: SpeciesList, kingdom: Kingdom) {
        TODO("Not yet implemented")
    }

    /**
     * Upload a list of kingdoms
     */
    override fun uploadKingdoms(kingdoms: KingdomList) {
        TODO("Not yet implemented")
    }

}
