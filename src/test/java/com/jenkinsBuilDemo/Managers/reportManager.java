package com.jenkinsBuilDemo.Managers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class reportManager {
    private static ExtentReports extent;
    private static String currentReportFolder;
    private static String latestReportFolder;
    private static String timestamp;
    private static ThreadLocal<ExtentReports> threadReporter = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void createReport() throws IOException {

        timestamp = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
        currentReportFolder = System.getProperty("user.dir")+"/Reports/Run_"+timestamp;
        latestReportFolder = System.getProperty("user.dir")+"/Reports/Latest";

        if(Files.exists(Path.of(latestReportFolder))){
            FileUtils.deleteDirectory(new File(latestReportFolder));//delete latest folder if its not empty
        }

        Files.createDirectories(Path.of(latestReportFolder));// create fresh latest folder
        Files.createDirectories(Path.of(currentReportFolder));//create fresh current report folder

        String currentReportFile = currentReportFolder+"/ExtentExecutionReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(currentReportFile);//create reporter object
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        threadReporter.set(extent);
    }

    public static ExtentReports getTestReporter() throws IOException {
        return threadReporter.get();
    }

    public static void setExtentTest(ExtentTest test1){
        test.set(test1);
    }

    public ExtentTest getExtentTest(){
        return test.get();
    }

    public static void generateCucumberReport(File jsonReport) throws IOException {
        File reportOutputDirectory = new File(currentReportFolder);
        Configuration config = new Configuration(reportOutputDirectory, "CucumberProject");
        config.addClassifications("Platform", "Windows");
        config.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(
                Collections.singletonList(jsonReport.getAbsolutePath()), config);
        reportBuilder.generateReports();
        FileUtils.copyDirectory(new File(currentReportFolder), new File(latestReportFolder));
        System.out.println("✅ Advanced reports generated at: "+currentReportFolder);
    }

}
