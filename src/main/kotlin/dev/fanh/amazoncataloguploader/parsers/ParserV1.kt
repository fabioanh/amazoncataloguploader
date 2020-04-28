package dev.fanh.amazoncataloguploader.parsers

import com.google.gson.*
import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.utils.isoLocation
import dev.fanh.amazoncataloguploader.utils.languageFromJsonV1

public class ParserV1 : Parser {
    override val version: ParserVersion = ParserVersion.V1
    private val gson: Gson = GsonBuilder().create()

    private fun JsonObject.getNullable(key: String): JsonElement? {
        val value: JsonElement = this.get(key) ?: return null

        if (value.isJsonNull) {
            return null
        }

        return value
    }

    override fun parseSpecies(filename: String): Species {
        val speciesDataObject = gson.fromJson(jsonFileReader(filename), JsonObject::class.java)
        return Species(version = this.version.name,
                id = speciesDataObject.get("_id").asString,
                behaviour = speciesDataObject.get("behaviorApprovedInUse")?.asJsonObject?.get("behavior")?.asJsonObject?.get("behaviorUnstructured")?.asString,
                commonNames = extractCommonNames(speciesDataObject.get("commonNames")),
                description = speciesDataObject.get("abstractApprovedInUse")?.asJsonObject?.get("abstract")?.asString,
                endangeredStatus = extractEndangeredStatus(speciesDataObject.get("threatStatusApprovedInUse")?.asJsonObject?.get("threatStatus")?.asJsonArray),
                feeding = speciesDataObject.get("feedingApprovedInUse")?.asJsonObject?.get("feeding")?.asJsonObject?.get("feedingUnstructured")?.asString,
                fullDescription = speciesDataObject.get("fullDescriptionApprovedInUse")?.asJsonObject?.get("fullDescription")?.asJsonObject?.getNullable("fullDescriptionUnstructured")?.asString,
                habitat = speciesDataObject.get("habitatsApprovedInUse")?.asJsonObject?.get("habitats")?.asJsonObject?.get("habitatUnstructured")?.asString,
                imageURLs = extractImages(speciesDataObject.get("ancillaryDataApprovedInUse")?.asJsonObject?.get("ancillaryData")?.asJsonArray),
                lifecycle = speciesDataObject.get("lifeCycleApprovedInUse")?.asJsonObject?.get("lifeCycle")?.asJsonObject?.get("lifeCycleUnstructured")?.asString,
                lifeForm = speciesDataObject.get("lifeFormApprovedInUse")?.asJsonObject?.get("lifeForm")?.asJsonObject?.get("lifeFormUnstructured")?.asString,
                migration = speciesDataObject.get("migratoryApprovedInUse")?.asJsonObject?.get("migratory")?.asJsonObject?.get("migratoryUnstructured")?.asString,
                reproduction = speciesDataObject.get("reproductionApprovedInUse")?.asJsonObject?.get("reproduction")?.asJsonObject?.get("reproductionUnstructured")?.asString,
                scientificName = speciesDataObject.get("scientificNameSimple")?.asString
                        ?: throw Exception("Scientific Name should not be null"))
    }

    private fun extractEndangeredStatus(endangeredStatusElements: JsonArray?): List<LocalisedValue>? {
        val endangeredStatus = ArrayList<LocalisedValue>()
        endangeredStatusElements?.forEach { elem ->
            val location = elem.asJsonObject.get("threatStatusAtomized")?.asJsonObject?.get("appliesTo")?.asJsonObject?.get("country")?.asString
            val threatValue = elem.asJsonObject.get("threatStatusAtomized")?.asJsonObject?.get("threatCategory")?.asJsonObject?.get("measurementValue")?.asString
            if (threatValue != null && location != null) {
                endangeredStatus.add(LocalisedValue(threatValue, isoLocation(location)))
            }
        }
        return if (endangeredStatus.isEmpty()) null else endangeredStatus
    }

    private fun extractImages(imageElements: JsonArray?): List<String>? {
        val result: ArrayList<String>? = ArrayList<String>()
        imageElements?.forEach { elem ->
            val obj = elem.asJsonObject
            obj.get("source")?.asString?.let { result?.add(it) }
            obj.get("mediaURL")?.asJsonArray?.map { e -> e.asString }?.let { result?.addAll(it) }
        }
        return if (result!!.isEmpty()) null else result
    }

    override fun parseSpeciesList(filename: String): SpeciesList {
        val speciesArrayJsonObject = gson.fromJson(jsonFileReader(filename), Array<JsonObject>::class.java)
        val speciesDataObjects = ArrayList<SpeciesListDataObject>()
        return SpeciesList(this.version.name,
                speciesArrayJsonObject.map { obj ->
                    SpeciesListDataObject(obj.get("_id").asString,
                            extractCommonNames(obj.get("commonNames")),
                            obj.get("scientificNameSimple").asString)
                })
    }

    private fun extractCommonNames(jsonCommonNames: JsonElement?): List<LanguagedValue>? {

        return jsonCommonNames?.asJsonArray?.map { obj -> LanguagedValue(obj.asJsonObject.get("name").asString, languageFromJsonV1(obj.asJsonObject.get("language"))) }
    }

    override fun parseKingdoms(filename: String): KingdomList {
        val kingdoms = gson.fromJson(jsonFileReader(filename), Array<String>::class.java)
        if (kingdoms.isEmpty()) {
            throw Exception("The list of kingdoms cannot be empty")
        }
        return KingdomList(this.version.name, kingdoms.asList())
    }

}