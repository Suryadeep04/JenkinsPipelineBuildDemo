package com.jenkinsBuilDemo.hooks;

import com.jenkinsBuilDemo.Base.baseClass;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.testng.annotations.AfterSuite;

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

    @AfterSuite
    public void generateReport() {
        try {
            File jsonReport = new File("target/cucumber-report/cucumber.json");

            if (!jsonReport.exists()) {
                System.out.println("❌ No cucumber.json found. Skipping report generation.");
                return;
            }

            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()).replaceAll("-","");
            generateReport("target/Reports/"+timestamp,jsonReport);
            System.out.println("✅ Advanced Cucumber report generated at: target/Reports/"+timestamp);

            Path latestPath = Paths.get("target/Reports/Latest");
            if (Files.exists(latestPath)) {
                deleteFolder(latestPath); // delete existing 'latest'
            }
            Files.createDirectories(latestPath);

            generateReport("target/Reports/Latest",jsonReport);
            System.out.println("📂 'latest' report available at: " + latestPath.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Report generation failed");
        }
    }

    private void generateReport(String reportPath, File jsonReport) throws IOException {
        File reportOutputDirectory = new File(reportPath);
        Configuration config = new Configuration(reportOutputDirectory, "CucumberProject");
        config.addClassifications("Platform", "Windows");
        config.addClassifications("Browser", readProperty().getProperty("browser"));
        config.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(
                Collections.singletonList(jsonReport.getAbsolutePath()), config);
        reportBuilder.generateReports();
    }
    private void deleteFolder(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
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
