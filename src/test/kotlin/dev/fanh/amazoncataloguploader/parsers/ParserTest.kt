package dev.fanh.amazoncataloguploader.parsers

import org.junit.Test
import kotlin.test.assertTrue

class ParserTest {
    @Test
    fun rightParserInstance() {
        assertTrue(ObjectFileParserFactory.getParser(ParserVersion.V1) is ParserV1, "Parser should be instance of V1 Parser")
    }
}