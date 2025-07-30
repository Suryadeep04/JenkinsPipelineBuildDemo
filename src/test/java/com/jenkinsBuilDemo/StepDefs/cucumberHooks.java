package com.jenkinsBuilDemo.StepDefs;

import com.aventstack.extentreports.ExtentTest;
import com.jenkinsBuilDemo.Base.baseClass;
import com.jenkinsBuilDemo.Managers.reportManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;
import java.time.Duration;



public class cucumberHooks extends baseClass {

    @Before(order = 1)
    public void setupBrowser(){
        try {
            String browser = configProp.getProperty("browser");
           switch (browser){
               case "chrome":
                   chromeBrowserSetup();
                   break;
               case "edge":
                   edgeBrowserSetup();
                   break;
               default:
                   chromeBrowserSetup();
                   break;
           }
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.get(configProp.getProperty("url"));
            System.out.println("browser setup completed");
        }catch (Exception e){}
    }

    @Before(order = 2)
    public void reportHook(Scenario scenario) throws IOException {
        ExtentTest test = reportManager.getTestReporter().createTest(scenario.getName());
        reportManager.setExtentTest(test);
    }

    @After
    public void tearDown(){
        System.out.println("Closing down browser");
        driver.quit();
    }

}
