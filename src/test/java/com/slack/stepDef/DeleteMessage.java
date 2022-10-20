package com.slack.stepDef;

import com.slack.utils.CommonUtils;
import com.slack.utils.PayLoadUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class DeleteMessage {

Response response;
    @When("user sends and delete message")
    public void user_sends_and_delete_message() {
        response=  RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayLoadUtils.getSlackMessagePayLoad(CommonUtils.readProperty("messageDelete3"),CommonUtils.readProperty("channel")))
                .header("Authorization" , CommonUtils.readProperty("token"))
                .when()
                .post()
                .then().log().all().extract().response();
        JsonPath parseResponse= response.jsonPath();
       String ts = parseResponse.getString("ts");

        RestAssured.basePath= "api/chat.delete";
response=RestAssured.given().accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .header("Authorization",CommonUtils.readProperty("token"))
        .body(PayLoadUtils.deleteSlackMessagePayLoad(CommonUtils.readProperty("channel"),ts))
        .when()
        .post()
        .then()
        .log().all().extract().response();


    }
    @Then("status code is {int} is here")
    public void status_code_is_is_here(Integer expectedStatusCode) {
        Integer actualStatusCode= response.statusCode();
        Assert.assertEquals(expectedStatusCode ,actualStatusCode);

    }
    @Then("message is successfully deleted")
    public void message_is_successfully_deleted() {
     JsonPath jsonPath = response.jsonPath();
     boolean okIsTrue=jsonPath.getBoolean("ok");
     Assert.assertTrue(okIsTrue);


}
}