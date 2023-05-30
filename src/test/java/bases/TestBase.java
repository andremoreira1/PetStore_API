package bases;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    private static String BASE_URI = "https://petstore.swagger.io/v2"; //URL da API

    @BeforeMethod
    public void PetStoreAPIBaseURI() {
        // Definição da URL que utilizaremos nos testes seguintes
        RestAssured.baseURI = BASE_URI;}

}
