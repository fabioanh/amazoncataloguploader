package dev.fanh.amazoncataloguploader.uploadclients

import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList
import java.io.File
import java.io.Reader

interface UploadClient {
    val type: UploadClientType

    /**
     * Upload a list of species
     */
    fun uploadSpecies(species: List<Species>)

    /**
     * Upload the SpeciesList information
     */
    fun uploadSpeciesList(speciesList: SpeciesList)

    /**
     * Upload a list of kingdoms
     */
    fun uploadKingdoms(kingdoms:KingdomList)
}
