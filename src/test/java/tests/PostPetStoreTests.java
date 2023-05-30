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

public class PostPetStoreTests extends TestBase {

    DataUtils dataUtils;

    @Test
    public void postStoreOrder() {
        // Preparação do teste
        dataUtils = new DataUtils();
        dataUtils.insertOrder();
        dataUtils.deleteOrder(10540);

        // Parametros
        int statusCodeEsperado = HttpStatus.SC_OK;
        int orderId = 10540;
        String endpoint = "/store/order";
        String requestBody = "{\n" +
                "  \"id\": " + orderId + ",\n" +
                "  \"petId\": 1,\n" +
                "  \"quantity\": 50,\n" +
                "  \"shipDate\": \"2023-05-30T00:08:17.744Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        // Fluxo
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then();

        // Validação
        response.statusCode(statusCodeEsperado);
        response.body("id", Matchers.equalTo(orderId));

    }


    @AfterTest
    public void limpaOrderConfig() {
        // Parametros
        int orderId = 10540;

        // Fluxo
        dataUtils = new DataUtils();
        dataUtils.insertOrder();
        dataUtils.deleteOrder(orderId);
    }
}
