package dev.fanh.amazoncataloguploader.uploadclients

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import dev.fanh.amazoncataloguploader.data.Kingdom
import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList

class S3UploadClient : UploadClient {
    override val type: UploadClientType = UploadClientType.S3

    private val s3Client:AmazonS3 = AmazonS3ClientBuilder.standard().build()

    /**
     * Upload a list of species
     */
    override fun uploadSpecies(species: List<Species>, kingdom: Kingdom) {
//        TODO("Not yet implemented")
    }

    /**
     * Upload the SpeciesList information
     */
    override fun uploadSpeciesList(speciesList: SpeciesList, kingdom: Kingdom) {
//        TODO("Not yet implemented")
    }

    /**
     * Upload a list of kingdoms
     */
    override fun uploadKingdoms(kingdoms: KingdomList) {
//        TODO("Not yet implemented")
    }
}
