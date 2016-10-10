package com.cucumber.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.cucumber.configuration.AppConfig;
import com.cucumber.service.FileService;


public class WebDriverUtils{

	WebDriver driver;
	
	public WebDriverUtils(WebDriver driver){
		this.driver = driver;
	}
	
	public void takeStreenShot(String screenShotName){
		File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			AbstractApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
			FileService service = (FileService) context.getBean("fileService");
			//FileUtils.copyFile(screenShotFile, new File("/Users/gregorydombchik/Documents/workspace_luna/MavenJunitPrototypePractice/screenshots/" + screenShotName + ".png"));
			FileUtils.copyFile(screenShotFile, new File(service.getScreenShot() + screenShotName + ".png"));
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
	
	//Return the element found by locator filtered by attribute value
	public WebElement getWebElementByLocatorFilteredByAttributeValue(By locator,String attribute, String attributeValue){
		WebElement webElementReturned=null;
		List<WebElement> webElements = driver.findElements(locator);
		for(WebElement webElement : webElements){
			if(webElement.getAttribute(attribute).contains(attributeValue)){
				webElementReturned = webElement;
			}
			//System.out.println("-----> " + webElement.getAttribute("value"));
		}
		return webElementReturned;
	};
	
	public List<WebElement> getWebElementsByLocatorFilteredByAttributeValue(By locator,String attribute, String attributeValue){
		List<WebElement> webElementReturned = new ArrayList<WebElement>();
		List<WebElement> webElements = driver.findElements(locator);
		for(WebElement webElement : webElements){
			if(webElement.getAttribute(attribute).contains(attributeValue)){
				webElementReturned.add(webElement);
			}
		}
		return webElementReturned;
	};
}
