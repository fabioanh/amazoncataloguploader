package dev.fanh.amazoncataloguploader.uploadclients

enum class UploadClientType { S3, DYNAMO_DB }

interface UploadClientFactory{
    fun getUploadClient(clientType:UploadClientType):UploadClient

}

object ObjectUploadClientFactory:UploadClientFactory {
    override fun getUploadClient(clientType: UploadClientType): UploadClient {
        return when(clientType){
            UploadClientType.S3 -> S3UploadClient()
            UploadClientType.DYNAMO_DB -> DynamoDBUploadClient()
        }
    }
}