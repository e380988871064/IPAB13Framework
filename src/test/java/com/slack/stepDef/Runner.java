package com.slack.stepDef;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/slack.features",
        glue = "com/slack/stepDef",
        tags = "@get",

        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class Runner {



}
