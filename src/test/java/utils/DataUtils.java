package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DataUtils {

    public void insertPet() {
        // Chamada POST para incluir dados na API
        String requestBody = """
                {
                  "id": 9214889,
                  "category": {
                    "id": 9214889,
                    "name": "categoria pet"
                  },
                  "name": "nome pet",
                  "photoUrls": [
                    "https://example.com/rex.jpg"
                  ],
                  "tags": [
                    {
                      "id": 9214889,
                      "name": "tag pet"
                    }
                  ],
                  "status": "pending"
                }""";

        String endpoint = "/pet";

        //Envio da Requisição
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200);
    }

    public void deletePet(int id) {
        RestAssured.given()
                .pathParam("petId", id)
                .when()
                .delete("/pet" + "/{petId}")
                .then()
                .statusCode(200);
    }

    public void insertOrder() {
        String requestBody = "{\n" +
                "  \"id\": 10540 ,\n" +
                "  \"petId\": 1,\n" +
                "  \"quantity\": 50,\n" +
                "  \"shipDate\": \"2023-05-30T00:08:17.744Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";
        String endpoint = "/store/order";

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200);
    }

    public void deleteOrder(int orderId) {
        RestAssured.given()
                .pathParam("orderId", orderId)
                .when()
                .delete("/store/order" + "/{orderId}")
                .then()
                .statusCode(200);
    }

}
