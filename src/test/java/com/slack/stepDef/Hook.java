package com.slack.stepDef;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hook {


    @Before
    public void setup(){
        RestAssured.baseURI="https://slack.com/";
                RestAssured.basePath ="api/chat.postMessage";
    }
}
