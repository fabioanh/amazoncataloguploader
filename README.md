# amazoncataloguploader
Kotlin project to upload the information required for the amazon catalog to AWS (S3 and DynamoDB so far)

## Useful AWS CLI and Misc commands

### DYNAMO DB

docker run -p 8000:8000 amazon/dynamodb-local

aws configure

`__aws dynamodb scan --table-name species --select "COUNT" --endpoint-url http://localhost:8000__`

`aws dynamodb list-tables --endpoint-url http://localhost:8000`

```aws dynamodb put-item --table-name kingdoms \
--item '{"name": {"S": "Animalia"}}' \
--endpoint-url http://localhost:8000
```

```aws dynamodb query --table-name kingdoms \
--key-condition-expression '#kingdomName = :name' \
--expression-attribute-names '{"#kingdomName": "name"}' \
--expression-attribute-values '{":name": {"S": "Animalia"}}' \
--endpoint-url http://localhost:8000
```

#### Create Tables
```aws dynamodb create-table \
--table-name kingdom \
--attribute-definitions \
AttributeName=name,AttributeType=S \
--key-schema AttributeName=name,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000
```

```aws dynamodb create-table \
--table-name speciesSummary \
--attribute-definitions AttributeName=id,AttributeType=S \
--key-schema AttributeName=id,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000
```

```aws dynamodb create-table \
--table-name species \
--attribute-definitions AttributeName=id,AttributeType=S \
--key-schema AttributeName=id,KeyType=HASH \
--provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
--endpoint-url http://localhost:8000
```

#### Delete Tables
```aws dynamodb delete-table \
--table-name kingdom \
--endpoint-url http://localhost:8000
```

```aws dynamodb delete-table \
--table-name speciesSummary \
--endpoint-url http://localhost:8000
```

```aws dynamodb delete-table \
--table-name species \
--endpoint-url http://localhost:8000
```