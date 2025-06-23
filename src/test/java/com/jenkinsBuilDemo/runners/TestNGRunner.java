package com.jenkinsBuilDemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"Features/jenkinsbuilddemo.feature"},
        glue = {"com.jenkinsBuilDemo.StepDefs"},
        plugin = {
                "pretty",
                "json:target/cucumber-report/cumcumber.json"
        },
        tags = "@TC1"
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

}