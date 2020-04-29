package dev.fanh.amazoncataloguploader.uploadclients

import org.junit.Test
import kotlin.test.assertEquals

class DynamoDBUploadClientTest{
    @Test
    fun getType() {
        // when
        val client = DynamoDBUploadClient()
        // then
        assertEquals(UploadClientType.DYNAMO_DB, client.type, "Wrong type given to upload client")
    }
}