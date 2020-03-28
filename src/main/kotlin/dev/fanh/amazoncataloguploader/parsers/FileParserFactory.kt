package dev.fanh.amazoncataloguploader.parsers

enum class ParserVersion { V1 }

interface FileParserFactory{
    fun getParser(version:ParserVersion):Parser

}

object ObjectFileParserFactory:FileParserFactory {

    override fun getParser(version: ParserVersion):Parser{
        return TODO()
//        return when(version){
//            ParserVersion.V1 -> ParserV1()
//        }
    }
}