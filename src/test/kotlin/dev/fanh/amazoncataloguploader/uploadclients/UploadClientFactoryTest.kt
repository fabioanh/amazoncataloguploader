package dev.fanh.amazoncataloguploader.uploadclients

import dev.fanh.amazoncataloguploader.parsers.ObjectFileParserFactory
import dev.fanh.amazoncataloguploader.parsers.ParserV1
import dev.fanh.amazoncataloguploader.parsers.ParserVersion
import org.junit.Test
import kotlin.test.assertTrue

class UploadClientFactoryTest{

    @Test
    fun rightS3ClientInstance() {
        // when
        val uploadClient = ObjectUploadClientFactory.getUploadClient(UploadClientType.S3)
        // then
        assertTrue( uploadClient is S3UploadClient, "Upload client should be of type S3UploadClient")
    }

    @Test
    fun rightDynamoClientInstance() {
        // when
        val uploadClient = ObjectUploadClientFactory.getUploadClient(UploadClientType.DYNAMO_DB)
        // then
        assertTrue( uploadClient is DynamoDBUploadClient, "Upload client should be of type DynamoDBUploadClient")
    }
}