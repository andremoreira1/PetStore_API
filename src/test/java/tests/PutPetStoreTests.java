package tests;

import bases.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utils.DataUtils;

import static io.restassured.RestAssured.given;

public class PutPetStoreTests extends TestBase {

    DataUtils dataUtils;

    @Test
    public void putAtualizarPetExistente() {
        // Preparação do teste
        dataUtils = new DataUtils();
        dataUtils.insertPet();

        // Parametros
        int statusCodeEsperado = HttpStatus.SC_OK;
        String endpoint = "/pet";
        int id = 9214889;
        String categoryName = "categoria pet alterado put";
        String petName = "nome pet";
        String photoUrl = "string";
        int tagId = 9214889;
        String tagName = "tag pet";
        String status = "pending";

        String requestBody = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": " + id + ",\n" +
                "    \"name\": \"" + categoryName + "\"\n" +
                "  },\n" +
                "  \"name\": \"" + petName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"" + photoUrl + "\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": " + tagId + ",\n" +
                "      \"name\": \"" + tagName + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";

        // Fluxo
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then();

        // Validação
        response.statusCode(statusCodeEsperado);
        response.body("category.name", Matchers.equalTo(categoryName));
    }

    @AfterTest
    public void limpaPetConfig() {
        // Parametros
        int id = 9214889;

        // Fluxo
        dataUtils = new DataUtils();
        dataUtils.insertPet();
        dataUtils.deletePet(id);
    }
}
