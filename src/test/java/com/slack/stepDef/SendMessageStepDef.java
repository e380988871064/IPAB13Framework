package com.slack.stepDef;

import com.slack.utils.CommonUtils;
import com.slack.utils.PayLoadUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class SendMessageStepDef {

    Response response ;

    @Given("user has valid slack url")
    public void user_has_valid_slack_url() {
        Assert.assertNotNull(RestAssured.baseURI);
        Assert.assertNotNull(RestAssured.basePath);
    }
    @When("user send a message to Slack channel")
    public void user_send_a_message_to_slack_channel() {

      response=  RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(PayLoadUtils.getSlackMessagePayLoad(CommonUtils.readProperty("message"),CommonUtils.readProperty("channel")))
        .header("Authorization" , CommonUtils.readProperty("token"))
        .when()
        .post()
              .then().log().all().extract().response();

    }

    @Then("status code is {int}")
    public void status_code_is(Integer expectedStatusCode) {
       Integer actualStatusCode =  response.statusCode();
       Assert.assertEquals(expectedStatusCode ,actualStatusCode);


    }
    @Then("message is successfully delivered")
    public void message_is_successfully_delivered() {
        JsonPath parseResponse =response.jsonPath();
        boolean isOk= parseResponse.getBoolean("ok");
        Assert.assertTrue(isOk);
    }


}
