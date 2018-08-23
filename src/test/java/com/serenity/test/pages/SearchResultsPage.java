package com.serenity.test.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import TestBase.TestBase;

@Component
public class SearchResultsPage extends TestBase
{

	WebDriver driver;
	

	
	@FindBy(xpath = ".//ul[@class='listView flights']/li")
	List<WebElement> flightList;
	
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
	public void bookFlight(String value) throws InterruptedException
	{
	
		Thread.sleep(15000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
        for(int i=1;i<flightList.size();i++) 
        {
             
        	WebElement fieldDepartTime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='listView flights']/li["+(i)+"]/table/tbody[2]/tr[1]/th[2]")));
           
           if(fieldDepartTime.getText().equals(value))
           {
        	   
               driver.findElement(By.xpath("//ul[@class='listView flights']/li["+(i)+"]/table/tbody[2]/tr[2]/td[3]/button")).click();
  
           }
		
       }
}
}
