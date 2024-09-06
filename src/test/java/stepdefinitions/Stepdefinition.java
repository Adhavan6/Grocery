package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Stepdefinition {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private Response response;

    @Given("the user registration endpoint is available")
    public void theUserRegistrationEndpointIsAvailable() {
        RestAssured.baseURI = "http://localhost:6003";
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    @When("I register a user with username {string}, password {string}, and email {string}")
    public void iRegisterAUser(String username, String password, String email) {
        response = given()
            .spec(requestSpec)
            .body(new User(username, password, email))
            .post("/users/register");
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response body should contain username {string} and email {string}")
    public void theResponseBodyShouldContainUsernameAndEmail(String username, String email) {
        response.then()
            .body("username", equalTo(username))
            .body("email", equalTo(email));
    }
   
    @Given("the user login endpoint is available")
    public void theUserLoginEndpointIsAvailable() {
        // Already set up in the Given method for registration
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        response = given()
            .spec(requestSpec)
            .body(new User(username, password, null))
            .post("/users/login");
    }

    @Then("the response body should contain message {string}")
    public void theResponseBodyShouldContainMessage(String message) {
        response.then().body("message", equalTo(message));
    }
    
    @Given("the user update endpoint is available")
    public void theUserUpdateEndpointIsAvailable() {
        // Already set up in the Given method for registration
    }

    @When("I update user with id {int} with username {string} and email {string}")
    public void iUpdateUserWithIdWithUsernameAndEmail(int userId, String username, String email) {
        response = given()
            .spec(requestSpec)
            .body(new User(username, null, email))
            .put("/users/" + userId);
    }
       
    @Given("the user delete endpoint is available")
    public void theUserDeleteEndpointIsAvailable() {
        // Already set up in the Given method for registration
    }

    @When("I delete user with id {int}")
    public void iDeleteUserWithId(int userId) {
        response = given()
            .spec(requestSpec)
            .delete("/users/" + userId);
    }

    @Then("the response status code should be {int} for deletion")
    public void theResponseStatusCodeShouldBeForDeletion(int statusCode) {
        response.then().statusCode(statusCode);
    }
   
    @Given("the movie post endpoint is available")
    public void theMoviePostEndpointIsAvailable() {
        // Already set up in the Given method for registration
    }

    @When("I post a movie with title {string}, category {string}, price {double}, and stockQuantity {int}")
    public void iPostAMovieWithDetails(String name, String category, double price, int stockQuantity) {
        response = given()
            .spec(requestSpec)
            .body(new Product(name, category, price, stockQuantity))
            .post("/products");
    }

    @Then("the response body should contain name {string} and category {string}")
    public void theResponseBodyShouldContainNameAndCategory(String name, String category) {
        response.then()
            .body("name", equalTo(name))
            .body("category", equalTo(category));
    }
    
    @Given("the movie get by ID endpoint is available")
    public void theMovieGetByIdEndpointIsAvailable() {
        // Already set up in the Given method for registration
    }

    @When("I get the product with ID {int}")
    public void iGetTheProductWithId(int productId) {
        response = given()
            .spec(requestSpec)
            .get("/products/" + productId);
    }

    @Then("the response body should contain name {string}")
    public void theResponseBodyShouldContainName(String name) {
        response.then().body("name", equalTo(name));
    }
    
    @Given("the rental post endpoint is available")
    public void theRentalPostEndpointIsAvailable() {
        // This is already covered by the general setup in the @Given for other endpoints
    }

    @When("I post a rental with userId {int}, productId {int}, quantity {int}, and totalPrice {double}")
    public void iPostARentalWithDetails(int userId, int productId, int quantity, double totalPrice) {
        response = given()
            .spec(requestSpec)
            .body(new Order(userId, productId, quantity, totalPrice))
            .post("/orders");
    }

    @Then("the response body should contain userId {int} and productId {int}")
    public void theResponseBodyShouldContainUserIdAndProductId(int userId, int productId) {
        response.then()
            .body("userId", equalTo(userId))
            .body("productId", equalTo(productId));
    }
    
    @Given("the orders get all endpoint is available")
    public void theOrdersGetAllEndpointIsAvailable() {
        // Already set up in the Given method for registration
    }

    @When("I get all orders")
    public void iGetAllOrders() {
        response = given()
            .spec(requestSpec)
            .get("/orders");
    }

    @Then("the response body should contain orders")
    public void theResponseBodyShouldContainOrders() {
        response.then().body("size()", greaterThan(0));
    }
}