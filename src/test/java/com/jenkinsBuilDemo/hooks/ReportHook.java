package com.jenkinsBuilDemo.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.jenkinsBuilDemo.Base.baseClass;
import com.jenkinsBuilDemo.Managers.reportManager;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Properties;

public class ReportHook {



    @BeforeSuite
    public void reportManager() throws IOException {
        reportManager.createReport();
        System.out.println("inside before suite hook");

    }

    @AfterSuite
    public void generateReport() {
        try {
            File jsonReport = new File("target/cucumber-report/cucumber.json");

            if (!jsonReport.exists()) {
                System.out.println("❌ No cucumber.json found. Skipping report generation.");
                return;
            }
            reportManager.generateCucumberReport(jsonReport);
            reportManager.getTestReporter().flush();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Report generation failed");
        }
    }

    private Properties readProperty() throws IOException {
        Properties configProp = new Properties();
        try{
            FileInputStream config = new FileInputStream("TestData/config.properties");
            configProp.load(config);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Not able to read property file, defaulting browser to Chrome");
            configProp.setProperty("browser","chrome");
        }
        return configProp;
    }

}
