package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.google.gson.Gson
import dev.fanh.amazoncataloguploader.data.*
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}

class DynamoDBUploadClient : UploadClient {

    override val type: UploadClientType = UploadClientType.DYNAMO_DB

    private val dynamoClient: DynamoDB

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
        val client = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            // Development configuration
//            .withRegion(Regions.SA_EAST_1)
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration("http://localhost:8000", Regions.SA_EAST_1.name)
            )
            .build()
        dynamoClient = DynamoDB(client)
    }

    constructor(dynamoClient: DynamoDB) {
        this.dynamoClient = dynamoClient
    }

    /**
     * Upload a list of species
     */
    override fun uploadSpecies(species: List<Species>, kingdom: Kingdom) {
        val speciesTable = dynamoClient.getTable("species")
        species
            .map { this.speciesObjectToItem(it) }
            .forEach { speciesTable.putItem(it) }
    }

    private fun speciesObjectToItem(species: Species): Item {
        val item = Item()
        item.withPrimaryKey("id", species.id)
        item.withString("kingdom", species.kingdom)
        item.withString("scientificName", species.scientificName)
        item.withString("version", species.version)

        if (!species.behaviour.isNullOrBlank()) {
            item.withString("behaviour", species.behaviour)
        }
        if (!species.description.isNullOrBlank()) {
            item.withString("description", species.description)
        }
        if (!species.feeding.isNullOrBlank()) {
            item.withString("feeding", species.feeding)
        }
        if (!species.fullDescription.isNullOrBlank()) {
            item.withString("fullDescription", species.fullDescription)
        }
        if (!species.habitat.isNullOrBlank()) {
            item.withString("habitat", species.habitat)
        }
        if (!species.lifecycle.isNullOrBlank()) {
            item.withString("lifecycle", species.lifecycle)
        }
        if (!species.lifeForm.isNullOrBlank()) {
            item.withString("lifeForm", species.lifeForm)
        }
        if (!species.migration.isNullOrBlank()) {
            item.withString("migration", species.migration)
        }
        if (!species.reproduction.isNullOrBlank()) {
            item.withString("reproduction", species.reproduction)
        }
        if (species.imageURLs != null) {
            item.withList("imageURLs", species.imageURLs)
        }
        if (species.commonNames != null) {
            item.withMap(
                "commonNames",
                species.commonNames.groupBy({ it.isoLanguage }, { it.value })
            )
        }
        if (species.endangeredStatus != null) {
            val endangeredStatusIterator = species.endangeredStatus.iterator()

            item.withMap(
                "endangeredStatus",
                species.endangeredStatus
                    .groupBy({ it.locale }, { it.value })
            )
        }
        return item
    }

    /**
     * Upload the SpeciesList information
     */
    override fun uploadSpeciesList(speciesList: SpeciesList, kingdom: Kingdom) {
        val speciesSummaryTable = dynamoClient.getTable("speciesSummary")
        speciesList.species
            .map { this.speciesSummaryObjectToItem(it, kingdom, speciesList.version) }
            .forEach { speciesSummaryTable.putItem(it) }
    }

    private fun speciesSummaryObjectToItem(
        speciesObject: SpeciesListDataObject,
        kingdom: Kingdom,
        version: String
    ): Item {
        val item = Item()
        item.withPrimaryKey("id", speciesObject.id)
        item.withString("scientificName", speciesObject.scientificName)
        item.withString("kingdom", kingdom.name)
        item.withString("version", version)
        if (speciesObject.names != null) {
            item.withMap(
                "names",
                speciesObject.names.groupBy({ it.isoLanguage }, { it.value })
            )
        }
        return item
    }

    /**
     * Upload a list of kingdoms
     */
    override fun uploadKingdoms(kingdoms: KingdomList) {
        val kingdomTable = dynamoClient.getTable("kingdom")
        kingdoms.kingdoms
            .map { this.kingdomObjectToItem(it, kingdoms.version) }
            .forEach { kingdomTable.putItem(it) }
    }

    private fun kingdomObjectToItem(kingdom: Kingdom, version: String): Item {
        val item = Item()
        item.withPrimaryKey("name", kingdom.name)
        item.withString("version", version)
        return item
    }

}
