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

public class UpdateMessage {

    Response response ;

    @When("user sends and updates message")
    public void user_sends_and_updates_message() {
        response=  RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayLoadUtils.getSlackMessagePayLoad(CommonUtils.readProperty("messageUpdate1"),CommonUtils.readProperty("channel")))
                .header("Authorization" , CommonUtils.readProperty("token"))
                .when()
                .post()
                .then().log().all().extract().response();
        JsonPath jsonPath = response.jsonPath();
        String ts= jsonPath.getString("ts");
        System.out.println(ts);


        RestAssured.basePath = "api/chat.update";

        response= RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", CommonUtils.readProperty("token"))
                .body(PayLoadUtils.updateSlackMessagePayLoad(CommonUtils.readProperty("messageUpdate2"),CommonUtils.readProperty("channel"),ts))
                .when()
                .post()
                .then()
                .log().all().extract().response();



    }
    @Then("status code is {int} should be")
    public void status_code_is_should_be(Integer expectedStatusCode) {
        Integer actualStatusCode= response.statusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);


    }
    @Then("message is successfully update")
    public void message_is_successfully_update() {
      JsonPath parseResponse = response.jsonPath();
      boolean isOk= parseResponse.getBoolean("ok");
      Assert.assertTrue(isOk);

    }




}
