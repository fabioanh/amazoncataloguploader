package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.google.gson.Gson
import dev.fanh.amazoncataloguploader.data.Kingdom
import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}

class S3UploadClient : UploadClient {


    override val type: UploadClientType = UploadClientType.S3

    private val gson = Gson()

    private val bucketCatalog: String = "amazoncatalogfanh"

    private val credentials: AWSCredentials = run {
        val props = javaClass.classLoader.getResourceAsStream("aws-credentials.properties").use {
            Properties().apply { load(it) }
        }
        BasicAWSCredentials(props["accessKey"] as String?, props["secretKey"] as String?)
    }

    private val s3Client: AmazonS3

    constructor() {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.SA_EAST_1)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .build()
    }

    constructor(s3Client: AmazonS3) {
        this.s3Client = s3Client
    }


    /**
     * Upload a list of species
     */
    override fun uploadSpecies(species: List<Species>, kingdom: Kingdom) {
        species.forEach { s ->
            s3Client.putObject(bucketCatalog, "/${kingdom.name.lowercase()}/species/${s.id}.json", gson.toJson(s))
            logger.info { "Species ${s.id} uploaded successfully to S3" }
        }
    }

    /**
     * Upload the SpeciesList information
     */
    override fun uploadSpeciesList(speciesList: SpeciesList, kingdom: Kingdom) {
        s3Client.putObject(bucketCatalog, "/${kingdom.name.lowercase()}/speciesList.json", gson.toJson(speciesList))
        logger.info { "Species List for ${kingdom.name} uploaded successfully to S3" }
        logger.info { "${speciesList.species.size} species found for $kingdom" }
    }

    /**
     * Upload a list of kingdoms
     */
    override fun uploadKingdoms(kingdoms: KingdomList) {
        s3Client.putObject(bucketCatalog, "/kingdoms.json", gson.toJson(kingdoms))
        logger.info("Kingdoms uploaded successfully to S3")
    }
}
