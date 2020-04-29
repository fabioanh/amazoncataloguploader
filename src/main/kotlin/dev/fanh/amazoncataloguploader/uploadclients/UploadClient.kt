package dev.fanh.amazoncataloguploader.uploadclients

import dev.fanh.amazoncataloguploader.data.Kingdom
import dev.fanh.amazoncataloguploader.data.KingdomList
import dev.fanh.amazoncataloguploader.data.Species
import dev.fanh.amazoncataloguploader.data.SpeciesList

interface UploadClient {
    val type: UploadClientType

    /**
     * Upload a list of species
     */
    fun uploadSpecies(species: List<Species>, kingdom: Kingdom)

    /**
     * Upload the SpeciesList information
     */
    fun uploadSpeciesList(speciesList: SpeciesList, kingdom: Kingdom)

    /**
     * Upload a list of kingdoms
     */
    fun uploadKingdoms(kingdoms:KingdomList)
}
