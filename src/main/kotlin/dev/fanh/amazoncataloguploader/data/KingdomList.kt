package dev.fanh.amazoncataloguploader.data

data class KingdomList(val version: String, val kingdoms: List<Kingdom>) {
}

data class Kingdom(val name:String){

}