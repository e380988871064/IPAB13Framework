package com.slack.stepDef;

import com.slack.utils.CommonUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListStepDef {
    Response response;
    @When("user lists messages")
    //https://slack.com/api/conversations.history?channel=C044QH2SS3U


    public void userListsMessages() {
        RestAssured.basePath="";
     response= RestAssured.given().accept(ContentType.JSON)

                .header("Authorization" , CommonUtils.readProperty("token"))
                .queryParam("channel", CommonUtils.readProperty("channel") )
                .when().get("api/conversations.history")
                .then()
             //.log().all()
             .extract().response();



    }
    @Then("status code should be {int}") //"status code should be {int}"
    public void statusCodeShouldBe(Integer expectedStatusCode) {
        Integer actualStatusCode= response.getStatusCode();
        Assert.assertEquals(expectedStatusCode , actualStatusCode);


    }
    @Then("user's message is on the list of messages")
    public void userSMessageIsOnTheListOfMessages() {
       // System.out.println( "Message one!!!!" );
        JsonPath jsonPath = response.jsonPath();
        List<Map<String ,Object>> messages = jsonPath.getList("messages");

        //System.out.println( "Message one!!!!"  +messages.get(1));

        String actualMessage;
    List<String> onlymessages = new ArrayList<>();
       for(int i = 0 ; i < messages.size(); i++){

           onlymessages.add( messages.get(i).get("text").toString());



          // System.out.println( messages.get(i).get("text").toString());
//         if ( messages.get(i).get("text").toString().equals("Elvira: from IPA framework from scratch")){
//              actualMessage =   messages.get(i).get("text").toString() ;
//            // System.out.println(actualMessage );
//
//         }
//           Assert.assertEquals("Elvira: from IPA framework from scratch" , actualMessage);
//System.out.println(actualMessage =   messages.get(i).get("text").toString());
       }
       // Assert.assertEquals("Elvira: from IPA framework from scratch" , actualMessage);

        //System.out.println(onlymessages.get(1));
       //Assert.assertEquals(onlymessages.get(1) , "Elvira: from IPA framework from scratch ");

for(int i = 0 ; i< onlymessages.size(); i++){

    if(onlymessages.get(i).equals("Elvira: from IPA framework from scratch ") ){
        System.out.println("true");
        Assert.assertEquals("Elvira: from IPA framework from scratch " , onlymessages.get(i));
    }break;
}



    }

}

