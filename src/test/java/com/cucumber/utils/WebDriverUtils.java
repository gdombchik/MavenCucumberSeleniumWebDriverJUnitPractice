package com.cucumber.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.propertyManager.PropertyManager;


public class WebDriverUtils{

	WebDriver driver;
	
	public WebDriverUtils(WebDriver driver){
		this.driver = driver;
	}
	
	public void takeStreenShot(String screenShotName){
		File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			PropertyManager propertyManager = new PropertyManager();
			propertyManager.generateProperty();
			//FileUtils.copyFile(screenShotFile, new File("/Users/gregorydombchik/Documents/workspace_luna/MavenJunitPrototypePractice/screenshots/" + screenShotName + ".png"));
			FileUtils.copyFile(screenShotFile, new File(propertyManager.getScreenShot() + screenShotName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WebElement waitUntilWebElementIsAvailable(By locator, int seconds){
		return (new WebDriverWait(driver,seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void sleep(int seconds){
		try{
			Thread.sleep(seconds * 1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Return the input element filtered by value
	public WebElement getInputBoxByAttributeValue(String attribute,String value){
		WebElement webElementReturned=null;
		List<WebElement> webElements = driver.findElements(By.tagName("input"));
		for(WebElement webElement : webElements){
			if(webElement.getAttribute(attribute).contains(value)){
				webElementReturned = webElement;
			}
			//System.out.println("-----> " + webElement.getAttribute("value"));
		}
		return webElementReturned;
	};
	
	//Return the link element filtered by value
	public WebElement getLinkByAttributeValue(String value){
		WebElement webElementReturned=null;
		List<WebElement> webElements = driver.findElements(By.cssSelector(".btn.btn-primary.btn-large"));
		for(WebElement webElement : webElements){
			//System.out.println("-----> " + webElement.getAttribute("href"));
			if(webElement.getAttribute("href").contains(value)){
				webElementReturned = webElement;
			}
		}
		return webElementReturned;
	};
	
	//Return the button element filtered by value
	public WebElement getButtonByAttributeValue(String value){
		WebElement webElementReturned=null;
		List<WebElement> webElements = driver.findElements(By.tagName("button"));
		for(WebElement webElement : webElements){
			//System.out.println("-----> " + webElement.getAttribute("class"));
			if(webElement.getAttribute("class").contains(value)){
				webElementReturned = webElement;
			}
		}
		return webElementReturned;
	};	
}
