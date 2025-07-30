package com.jenkinsBuilDemo.StepDefs;

import com.jenkinsBuilDemo.Managers.reportManager;
import com.jenkinsBuilDemo.PageClasses.pgSearchGoogle;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;


public class LoginStepDef {

    pgSearchGoogle searchGoogle;
    reportManager test;

    public LoginStepDef(){
        searchGoogle = new pgSearchGoogle();
        test = new reportManager();
    }

    @Given("^Navigate to the application page (.+)$")
    public void navigate_to_the_application_page(String input) throws Exception{
        //System.out.println("inside login stepdef");
        test.getExtentTest().info("inside login stepdef");
        //System.out.println("input from feature file: "+input);
        test.getExtentTest().pass("input from feature file: "+input);
        searchGoogle.launchApp();
    }
}
