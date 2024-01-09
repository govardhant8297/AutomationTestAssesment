package APITest;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class API_Tests {

	@Test
	public void getUsersList() {
		// Base URI
		RestAssured.baseURI = "https://reqres.in/api";

		// Perform the GET request to list users
		Response response = given().
				when().
				get("/users").
				then().
				extract().
				response();

		// Status code validation
		response.then().statusCode(200);

		// Print the response body
		System.out.println("List of users:");
		System.out.println(response.getBody().asString());
	}
	
	@Test
	public void getSingleUser() {
		// Base URI
		RestAssured.baseURI = "https://reqres.in/api";

		// Perform the GET request to list users
		Response response = given().
				when().
				get("users/2").
				then().
				extract().response();

		// Status code validation
		response.then().statusCode(200);

		// Print the response body
		System.out.println("List of users:");
		System.out.println(response.getBody().asString());
	}
	
	@Test
    public void createNewUser() {
        // Set the base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Request payload for creating a user
        String requestBody = "{\"name\": \"John Doe\", \"job\": \"Software Engineer\"}";

        // Perform the POST request to create a user
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        // Status code validation
        response.then().statusCode(201);

        // Print the response body
        System.out.println("Created user:");
        System.out.println(response.getBody().asString());

    }
	
}