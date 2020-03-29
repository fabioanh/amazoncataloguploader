package dev.fanh.amazoncataloguploader.data

data class SpeciesList(val version: String, val species: List<SpeciesListDataObject>) {
}

data class SpeciesListDataObject (val id: String, val names: List<LanguagedValue>){
}
