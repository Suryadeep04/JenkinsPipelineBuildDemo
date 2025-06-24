package com.jenkinsBuilDemo.StepDefs;

import com.jenkinsBuilDemo.PageClasses.pgSearchGoogle;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;


public class LoginStepDef {

    pgSearchGoogle searchGoogle;

    public LoginStepDef(){
        searchGoogle = new pgSearchGoogle();
    }

    @Given("^Navigate to the application page (.+)$")
    public void navigate_to_the_application_page(String input) throws Exception{
        System.out.println("inside login stepdef");
        System.out.println("input from feature file: "+input);
        searchGoogle.launchApp();
    }
}
