package dev.fanh.amazoncataloguploader.data

data class Species(val version: String,
                   val id: String,
                   val behaviour: String? = null,
                   val commonNames: List<LanguagedValue>,
                   val description: String,
                   val endangeredStatus: List<LocalisedValue>? = null,
                   val feeding: String? = null,
                   val fullDescription: String,
                   val habitat: String? = null,
                   val imageURLs: List<String>? = null,
                   val lifecycle: String? = null,
                   val lifeForm: String? = null,
                   val migration: String? = null,
                   val reproduction: String? = null,
                   val scientificName: String) {
}