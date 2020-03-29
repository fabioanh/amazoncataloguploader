package dev.fanh.amazoncataloguploader.data

class Species(version: String,
              id: String,
              behaviour: String? = null,
              commonNames: List<LanguagedValue>,
              description: String,
              endangeredStatus: List<LocalisedValue>? = null,
              feeding: String? = null,
              fullDescription: String,
              habitat: String? = null,
              imageURLs: List<String>? = null,
              lifecycle: String? = null,
              lifeForm: String? = null,
              migration: String? = null,
              reproduction: String? = null,
              scientificName: String) {
}