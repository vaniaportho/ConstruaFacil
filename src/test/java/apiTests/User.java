package apiTests;

import groovy.transform.ToString;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class User {
    String uri = "https://petstore.swagger.io/v2/user";
    int userId = 198339;
    String username = "peppa";
    String password = "peppa@123";
    String token = "";

    // Padrão
    // Given = Dado
    // .When = Quando
    // .Then = Então

    // Funções de Apoio
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 0)
    public void incluirUser() throws IOException { // Create - Post

        String jsonBody = lerJson("src/test/resources/data/user.json");

        given()                                                 //Dado
                .contentType("application/json")                //Tipo do conteúdo
                .log().all()                                    //Registrar tudo do envio
                .body(jsonBody)
        .when()                                                 //Quando
                .post(uri)                                      //Comando + endpoint
        .then()                                                 // Então
                .log().all()                                    // Registrar tudo da volta
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(Integer.toString(userId)))
        ;
    }

    @Test(priority = 1)
    public void consultarUser() {
        String username = "peppa";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + username)
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test(priority = 2)
    public void alterarUser() throws IOException {
        String jsonBody = lerJson("src/test/resources/data/newuser.json");

            given()
                    .contentType("application/json")
                    .log().all()
                    .body(jsonBody) //Json a ser transmitido para a alteração
            .when()
                    .put(uri)
            .then()
                    .log().all()
                    .statusCode(200)
                    .body("lastName", is("Porto Silva"))
            ;
        }

    @Test
    public void login(){

        String mensagem =
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/login?username=" + username + "&password=" + password)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
        .extract()
                .path("message")
        ;

        System.out.println("A mensagem é :" + mensagem);
        token = mensagem.substring(23);
        System.out.println("O token é:" + token);
    }
}


