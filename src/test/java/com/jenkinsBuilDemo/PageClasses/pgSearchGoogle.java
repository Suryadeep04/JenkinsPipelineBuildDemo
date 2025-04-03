package com.jenkinsBuilDemo.PageClasses;

import com.jenkinsBuilDemo.Base.baseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class pgSearchGoogle extends baseClass {

    WebElement searchbox = driver.findElement(By.xpath("//textarea[@class='gLFyf']"));


    public void launchApp() throws Exception{
        System.out.println("the browser app is launched");
        searchbox.sendKeys("hello");
        Thread.sleep(5000);

    }




}
