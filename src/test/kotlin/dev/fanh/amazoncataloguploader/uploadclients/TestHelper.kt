package dev.fanh.amazoncataloguploader.uploadclients

import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.parsers.ParserVersion

class TestHelper {
    companion object {
        fun getSpeciesList(): SpeciesList {
            val speciesList = SpeciesListDataObject(
                "id",
                listOf(LanguagedValue("a name", "en")), "A Scientific Name"
            )
            return SpeciesList(ParserVersion.V1.name, "animalia", listOf(speciesList))
        }

        fun getSingleKingdom(): KingdomList {
            return KingdomList(ParserVersion.V1.name, listOf(Kingdom("Animalia")))
        }

        fun getKingdoms(): KingdomList {
            return KingdomList(ParserVersion.V1.name, listOf(Kingdom("Animalia"), Kingdom("Herbalia")))
        }
    }
}