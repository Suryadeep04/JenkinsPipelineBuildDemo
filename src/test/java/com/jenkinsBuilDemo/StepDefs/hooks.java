package com.jenkinsBuilDemo.StepDefs;

import com.jenkinsBuilDemo.Base.baseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class hooks extends baseClass {

    @Before
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

    @After
    public void tearDown(){
        System.out.println("Closing down browser");
        driver.quit();
    }

}
