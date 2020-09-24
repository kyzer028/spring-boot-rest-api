# spring-boot-rest-api

This is a simple Java REST API based on Spring framework that answers to the prerequisite given by Alexander.

## 1. Developpement

Requirements :

JAVA 8+ / Maven 3

Run tests and build executable JAR :

    mvn clean package

Execute JAR and run web server (port 8080):

    java -jar target/spring-boot-rest-api-0.0.1.jar

## 2. API Documentation

### POST /actions/{type}

Execute some actions depending on a type.
Return executed actions and execution order.

#### Parameters

| Name | Type | Mandatory | Description
|---|---|---|---|
| type  | `String` | Yes | The action target entity type, available values : `OBSERVATION` / `FACT` / `EVENT`

#### Response

Array of Objects :

| Name | Type | Mandatory | Description
|---|---|---|---|
| name | `String` | Yes | Executed action name
| name | `int` | Yes | Action execution order


Curl command :
```shell
    curl -X POST \
    -H "accept: application/json" \
    "http://localhost:8080/actions/OBSERVATION"
```

Sample response (JSON):
```JSON
  [
    {
      "name": "DefaultObservationServiceImpl",
      "order": 1
    },
    {
      "name": "ComputeObservationServiceImpl",
      "order": 2
    },
    {
      "name": "PublishObservationServiceImpl",
      "order": 3
    }
  ]
```


### GET /requests/stats

Display the number of performed `/actions` requests by entity type.

#### Response

| Name | Type | Mandatory | Description
|---|---|---|---|
| observation | `int` | Yes | Number of requests performed with type `OBSERVATION`
| fact | `int` | Yes | Number of requests performed with type `FACT`
| event | `int` | Yes | Number of requests performed with type `EVENT`

Curl command :

```shell
    curl -H "accept: application/json" \
    "http://localhost:8080/requests/stats"
```

Sample response (JSON):
```JSON
    {
      "observation": 5,
      "fact": 0,
      "event": 3
    }
```