package tests;

import bases.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import utils.DataUtils;

import javax.xml.crypto.Data;

import static io.restassured.RestAssured.given;

public class GetPetStoreTests extends TestBase {

    DataUtils dataUtils;

    @Test
    public void getPetId() {
        // Preparação da massa
        dataUtils = new DataUtils();
        dataUtils.insertPet();

        // Parametros
        int petId = 9214889;
        int statusCodeEsperado = HttpStatus.SC_OK;

        // Fluxo
        ValidatableResponse response = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then();


        // Validação
        response.statusCode(statusCodeEsperado);
        response.body("id", Matchers.equalTo(petId));
    }

    @Test
    public void getPetNotFound() {
        // Parametros
        int petId = 9214889;
        int statusCodeEsperado = HttpStatus.SC_NOT_FOUND;
        String mensagemRetorno = "Pet not found";

        // Preparação do teste
        dataUtils = new DataUtils();
        dataUtils.insertPet();
        dataUtils.deletePet(petId);

        // Fluxo
        ValidatableResponse response = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then();

        // Validação
        response.statusCode(statusCodeEsperado);
        response.body("message", Matchers.equalTo(mensagemRetorno));
    }

    @Test
    public void getPetByStatusPending() {
        // Parametros
        int petId = 9214889;
        int statusCodeEsperado = HttpStatus.SC_OK;
        String statusBodyEsperado = "pending";

        // Preparação do teste
        dataUtils = new DataUtils();
        dataUtils.insertPet();

        // Fluxo
        ValidatableResponse response = given()
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then();

        // Validação
        response.statusCode(statusCodeEsperado);
        response.body("status", Matchers.equalTo(statusBodyEsperado));
    }

    @AfterTest
    public void limpaBancoConfig() {
        // Parametros
        int id = 9214889;

        // Fluxo
        dataUtils = new DataUtils();
        dataUtils.insertPet();
        dataUtils.deletePet(id);
    }
}