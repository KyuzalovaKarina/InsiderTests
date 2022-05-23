package com.insider.tests;

import com.insider.dto.Category;
import com.insider.dto.Pet;
import com.insider.dto.Tag;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class PetStore {

    private long id = 30;
    private long invalidId = 11111;
    private Pet pet;
    private String baseURI = "https://petstore.swagger.io/v2";

    public void preparePet() {
        RestAssured.baseURI = baseURI;
        Category category = new Category();
        category.setId(30L);
        category.setName("Insider category");

        Tag tag = new Tag();
        tag.setId(30L);
        tag.setName("Insider tag");

        pet = new Pet();
        pet.setId(id);
        pet.setName("Insider pet");
        pet.setStatus("available");
        pet.setCategory(category);
        pet.setTags(Arrays.asList(tag));
        pet.setPhotoUrls(Arrays.asList("insiderPet.jpg"));

    }

    public PetStore() {
        preparePet();
    }

    @Test(priority = 1)
    public void postPet() {
        RestAssured.given()
                .when()
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .with()
                .body(pet)//add pet object
                .post("/pet")
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .body()
                .assertThat()
                .extract().body().jsonPath();

    }

    @Test(priority = 2)
    public void getPet200() {
        get(baseURI + "/pet/" + pet.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .body()
                .body("id", equalTo(pet.getId().intValue()))
                .body("name", equalToCompressingWhiteSpace(pet.getName()))
                .body("status", equalToCompressingWhiteSpace(pet.getStatus()))
                .body("category.id", equalTo(pet.getCategory().getId().intValue()))
                .body("category.name", equalTo(pet.getCategory().getName()))
                .body("tags[0].id", equalTo(pet.getTags().get(0).getId().intValue()))
                .body("tags[0].name", equalTo(pet.getTags().get(0).getName()))
                .body("photoUrls[0]", containsStringIgnoringCase("insiderPet"));
    }

    @Test(priority = 3)
    public void updatePet() {

        RestAssured.given()
                .when()
                .contentType(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                .formParam("name", "updateInsiderPet")
                .formParam("status", "pending")
                .post("/pet/" + id)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }

    @Test(priority = 4)
    public void deletePet() {

        RestAssured.given()
                .when()
                .header("api_key", "token")
                .delete("/pet/" + id)
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test(priority = 5)
    public void deleteInvalidPet() {

        RestAssured.given()
                .when()
                .header("api_key", "token")
                .delete("/pet/" + id)
                .then()
                .assertThat()
                .statusCode(404);

    }

    @Test(priority = 6)
    public void getInvalidPet() {
        Response response =
                get("/pet/" + invalidId);
        assertTrue(response.getStatusCode() == 404);

    }


}
