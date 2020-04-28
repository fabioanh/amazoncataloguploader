package dev.fanh.amazoncataloguploader.uploadclients

import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList

class S3UploadClient : UploadClient {
    override val type: UploadClientType = UploadClientType.S3

    /**
     * Upload a list of species
     */
    override fun uploadSpecies(species: List<Species>) {
        TODO("Not yet implemented")
    }

    /**
     * Upload the SpeciesList information
     */
    override fun uploadSpeciesList(speciesList: SpeciesList) {
        TODO("Not yet implemented")
    }

    /**
     * Upload a list of kingdoms
     */
    override fun uploadKingdoms(kingdoms: KingdomList) {
        TODO("Not yet implemented")
    }
}
