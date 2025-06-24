package com.jenkinsBuilDemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"Features"},
        glue = {"com.jenkinsBuilDemo.StepDefs"},
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber-html-report.html",
                "json:target/cucumber-report/cucumber.json"
        },
        tags = "@TC1"
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

}