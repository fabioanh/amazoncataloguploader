package dev.fanh.amazoncataloguploader.parsers

import com.google.gson.*
import dev.fanh.amazoncataloguploader.data.*
import dev.fanh.amazoncataloguploader.utils.languageFromJsonV1

public class ParserV1 : Parser {
    override val version: ParserVersion = ParserVersion.V1
    private val gson: Gson = GsonBuilder().serializeNulls().create()

    override fun parseSpecies(filename: String): Species {
        val speciesDataObject = gson.fromJson(jsonFileReader(filename), JsonObject::class.java)
        return Species(version = this.version.name,
                id = speciesDataObject.get("_id").asString,
                behaviour = speciesDataObject.get("behaviorApprovedInUse")?.asJsonObject?.get("behavior")?.asJsonObject?.get("behaviorUnstructured")?.asString,
                commonNames = extractCommonNames(speciesDataObject.get("commonNames")),
                description = speciesDataObject.get("abstractApprovedInUse")?.asJsonObject?.get("abstract")?.asString ?: throw Exception("Description should not be null"),
                endangeredStatus = extractEndangeredStatus(speciesDataObject.get("threatStatusApprovedInUse")), //speciesDataObject.get("threatStatusValue")
                feeding = speciesDataObject.get("feedingApprovedInUse")?.asJsonObject?.get("feeding")?.asJsonObject?.get("feedingUnstructured")?.asString,
                fullDescription = speciesDataObject.get("fullDescriptionApprovedInUse")?.asJsonObject?.get("fullDescription")?.asJsonObject?.get("fullDescriptionUnstructured")?.asString ?: throw Exception("Full Description should not be null"),
                habitat = speciesDataObject.get("habitatsApprovedInUse")?.asJsonObject?.get("habitats")?.asJsonObject?.get("habitatsUnstructured")?.asString,
                imageURLs = extractImages(speciesDataObject.get("ancillaryDataApprovedInUse")?.asJsonObject?.get("ancillaryData")?.asJsonArray),
                lifecycle = speciesDataObject.get("lifeCycleApprovedInUse")?.asJsonObject?.get("lifeCycle")?.asJsonObject?.get("lifeCycleUnstructured")?.asString,
                lifeForm = speciesDataObject.get("lifeFormApprovedInUse")?.asJsonObject?.get("lifeForm")?.asJsonObject?.get("lifeFormUnstructured")?.asString,
                migration = speciesDataObject.get("migratoryApprovedInUse")?.asJsonObject?.get("migratory")?.asJsonObject?.get("migratoryUnstructured")?.asString,
                reproduction = speciesDataObject.get("reproductionApprovedInUse")?.asJsonObject?.get("reproduction")?.asJsonObject?.get("reproductionUnstructured")?.asString,
                scientificName = speciesDataObject.get("scientificNameSimple")?.asString ?: throw Exception("Scientific Name should not be null"))
    }

    private fun extractImages(imageElements: JsonArray?): List<String>? {
        TODO()
    }

    private fun extractEndangeredStatus(endangeredStatusElements: JsonElement): List<LocalisedValue>? {
        TODO()
    }

    override fun parseSpeciesList(filename: String): SpeciesList {
        val speciesArrayJsonObject = gson.fromJson(jsonFileReader(filename), Array<JsonObject>::class.java)
        val speciesDataObjects = ArrayList<SpeciesListDataObject>()
        return SpeciesList(this.version.name,
                speciesArrayJsonObject.map { obj -> SpeciesListDataObject(obj.get("_id").asString, extractCommonNames(obj.get("commonNames"))) })
    }

    private fun extractCommonNames(jsonCommonNames: JsonElement): List<LanguagedValue> {
        return jsonCommonNames.asJsonArray.map { obj -> LanguagedValue(obj.asJsonObject.get("name").asString, languageFromJsonV1(obj.asJsonObject.get("language"))) }
    }

    override fun parseKingdoms(filename: String): KingdomList {
        val kingdoms = gson.fromJson(jsonFileReader(filename), Array<String>::class.java)
        if (kingdoms.isEmpty()) {
            throw Exception("The list of kingdoms cannot be empty")
        }
        return KingdomList(this.version.name, kingdoms.asList())
    }

}