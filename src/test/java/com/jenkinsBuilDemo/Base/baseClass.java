package com.jenkinsBuilDemo.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

public class baseClass {

    String propFilepath = (System.getProperty("user.dir"))+"/TestData/config.properties";
    public static WebDriver driver;
    public static Properties configProp = null;

    //customized constructor
    public baseClass() {
        //read the config.properties file
        try {
            configProp = new Properties();
            FileInputStream file = new FileInputStream(propFilepath);
            configProp.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chromeBrowserSetup(){
        System.out.println("setting up chrome driver from the base class");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void edgeBrowserSetup(){
        System.out.println("setting up edge driver from the base class");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
