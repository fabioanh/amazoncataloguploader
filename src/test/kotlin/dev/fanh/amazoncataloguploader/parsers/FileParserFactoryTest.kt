package dev.fanh.amazoncataloguploader.parsers

import org.junit.Test
import kotlin.test.assertTrue

class FileParserFactoryTest {
    @Test
    fun rightParserInstance() {
        // when
        val parser = ObjectFileParserFactory.getParser(ParserVersion.V1)
        // then
        assertTrue( parser is ParserV1, "Parser should be instance of V1 Parser")
    }
}