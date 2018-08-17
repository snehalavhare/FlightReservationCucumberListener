package com.serenity.test.pages;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import TestBase.TestBase;

@Component
public class HomePage extends TestBase
{

	String stringValue;
	public static String today;
	WebDriver driver;
	
	@FindBy(xpath = "//*[@class='navGroup productNav withArrows']/li[1]/a[1]")
	WebElement FlightsOption;
	
	@FindBy(id="OneWay")
	private WebElement tripType;	
	
	@FindBy(id="FromTag")
	private WebElement fromCity;
	
	@FindBy(id="ToTag")
	private WebElement toCity;
	
	@FindBy(className = "icon ir datePicker")
	private WebElement departDate;
	
	@FindBy(id="Adults")
	private WebElement adults;
	
	@FindBy(id="Childrens")
	private WebElement childrens;
	
	@FindBy(id="id=Infants")
	private WebElement infants;
	
	@FindBy(id="SearchBtn")
	private WebElement searchButton;
	
	@FindBy(xpath="//h1[text()='Search flights']")
	private WebElement homeTitle;
	
	@FindBy(xpath="//a[@href='/flights']")
	private WebElement flightMenu;
	
	@FindBy(xpath = "//*[@class = 'autoComplete']/li/a")	
	private List<WebElement> fromCityList;
	
	@FindBy(xpath = "//*[@class = 'autoComplete'][2]/li/a")	
	private List<WebElement> toCityList;
	
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
	
	public void checkFlightsOption()
	{
		if(!FlightsOption.isSelected())
			FlightsOption.click();
		
	}
	public void select_TripType(String value)
	{
		
		
		tripType.sendKeys(value);
		
	}
	
	public void enter_FromCity(String value)
	{
		String[] splitString = value.split(",");
		String searchValue = splitString[0];
		fromCity.sendKeys(searchValue);
		
		//List<WebElement> fromCityList = driver.findElements(By.xpath("//*[@class = 'autoComplete']/li/a"));
		
		//fromCityList.forEach((element) -> System.out.println("The list of to cities are:  "+element.getText()));
			
		fromCityList.stream().filter(p -> repository.getProperty("fromCity").equals(value))
							.findFirst().ifPresent(ele -> ele.click());						
		
	}
	
	public void enter_ToCity(String value)
	{
		String[] splitString = value.split(",");
		String searchValue = splitString[0];
		toCity.sendKeys(searchValue);
		
		//toCityList.forEach((element) -> System.out.println("The list of to cities are:  "+element.getText()));
		
		//List<WebElement> toCityList = driver.findElements(By.xpath("//*[@class = 'autoComplete'][2]/li/a"));
		
		toCityList.stream().filter(p -> repository.getProperty("toCity").equals(value))
		.findFirst().ifPresent(ele -> ele.click());	
		
		
	}
	
	
	public void enter_Adults(int value)
	{
		Select select = new Select(adults);
		stringValue = Integer.toString(value);
		select.selectByValue(stringValue);
			
	}
	
	public void enter_Childrens(int value)
	{
		Select select = new Select(childrens);
		stringValue = Integer.toString(value);
		select.selectByValue(stringValue);
		
	}
	
	public void enter_Infants(int value)
	{
		
		Select select = new Select(infants);
		stringValue = Integer.toString(value);
		select.selectByValue(stringValue);
	}
	
	public void click_SearchButton()
	{
		searchButton.click();
	}

	public String getHomeTitle()
	{
		String title =  homeTitle.getText();
		return title;
	}
	
	public void selectFlightMenu()
	{
		flightMenu.click();
	}
	
	public void enter_DepartDate(String value)
	{
		
		String calendarMonth;
		String calendarYear;
		
		String[] dateSplit =  value.split(" ");
		String day = 	dateSplit[0];
		String month = 	dateSplit[1];		
		String year = 	dateSplit[2];	
		
		//System.out.println(day + "  "  + month + "  " + year);
		
		
		do
		{
			calendarMonth = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
			calendarYear = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[2]")).getText();
			
			//System.out.println(calendarMonth + " " + calendarYear);
			
			if(calendarMonth.equals(month) && calendarYear.equals(year))
			{
				
				List<WebElement> dateList = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td"));
				
				dateList.stream().filter(element -> element.getText().equals(day)).findFirst().ifPresent(ele -> ele.click());
				
				System.out.println("date selected is " + day + " " + month + " " + year);
				
				break;	
			}
			else
			{
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
			}
			
	
		}while(driver.findElement(By.xpath(".//*[@id='DepartDate']")).getText().isEmpty());

	
	}		
}
	

	
