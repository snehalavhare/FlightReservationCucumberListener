package com.serenity.test.pages;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import TestBase.TestBase;
import org.testng.Assert;

@Component
public class ReviewDetailsPage extends TestBase
{
	
	WebDriver driver;
	
	@FindBy(xpath = ".//span[text()='1']")
	WebElement ItineraryDetails;
	
	//@FindBy(className ="fRight action selectAddonButton")
	@FindBy(xpath = ".//button[@class='fRight action selectAddonButton']")
	WebElement addBaggageButton;
	
	@FindBy(id="MealButton")
	WebElement addMealButton;
	
	//@FindBy(xpath = ".//div[@class='col col5']/button")
	@FindBy(xpath = ".//*[@id='beforeMeals']/div/div[2]/button")
	WebElement selectSeatButton;
	

	@FindBy(xpath = ".//input[@id='insurance_box']")
	WebElement insuranceEnableCheckBox;
	 
	@FindBy(id="insurance_confirm")
	WebElement insuranceConfirmCheckBox;
	
	@FindBy(id="itineraryBtn")
	WebElement itineraryContinueButton;
	
	/*
	@FindBy(xpath = ".//span[text()='2']")
	WebElement emailAddressDetails;
	
	@FindBy(id="username")
	WebElement userNameTextBox;
	
	@FindBy(id="signinLabel")
	WebElement passwordCheckBox;
	
	@FindBy(id="subscribe_newsletter")
	WebElement subscribeCheckBox;
	
	@FindBy(id="password_1")
	WebElement passwordTextBox;
	
	@FindBy(id="LoginContinueBtn_1")
	WebElement loginContinueButton;
	
	@FindBy(xpath = ".//span[text()='3']")
	WebElement travellersDetails;
	
	@FindBy(id="AdultTitle1")
	WebElement adultTitleDropdown;
	
	@FindBy(id="AdultFname1")
	WebElement adultFirstName;
	
	@FindBy(id="AdultLname1")
	WebElement adultLastName;
	
	@FindBy(id="mobileNumber")
	WebElement adultMobileNumber;
	
	@FindBy(id="use_gst")
	WebElement GSTCheckBox;
	
	@FindBy(id="travellerBtn")
	WebElement travellerContinueButton;
	
	@FindBy(xpath = ".//span[text()='4']")
	WebElement paymentDetails;
	
	@FindBy(id="debitCardNumberDis")
	WebElement debitCardNumber;
	
	@FindBy(xpath = "a//text()='Debit Card']")
	WebElement debitCardMenu;
	
	@FindBy(id="DcExpirationMonth")
	WebElement debitExpiryMonth;
	
	@FindBy(id="DcExpirationYear")
	WebElement debitExpiryYear;
	
	@FindBy(id="BillName")
	WebElement debitCardHolderName;
	
	@FindBy(id="cvvCodeDc")
	WebElement debitCardCVV;
	
	@FindBy(id="consent")
	WebElement debitConsent;
	
	@FindBy(id="counter")
	WebElement finalPrice;
	
	@FindBy(id="paymentSubmit")
	WebElement makePaymentButton;
	*/
	
	public ReviewDetailsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean check_Itinerary()
	{
		return ItineraryDetails.isEnabled();
	}
	
	public void addBaggage(String value)
	{
		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		Assert.assertTrue(addBaggageButton.isDisplayed());
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(addBaggageButton)).click();
	
		driver.switchTo().frame("modal_window");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		List<WebElement> excessBaggageList = driver.findElements(By.xpath(".//li[@class='relative']/a/span[1]"));
		
		excessBaggageList.stream().filter(x -> x.getText().contains(value)).findFirst().ifPresent(ele -> ele.click());	
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement doneButton = driver.findElement(By.xpath(".//div[@class='col col4']/input"));
	
		try 
		{	
			if(doneButton.isEnabled())
			{
				doneButton.click();	
			}
		}catch(Exception e)
		{
				e.printStackTrace();
		}	
		
		driver.switchTo().defaultContent();
	
	}
	
	
	public void addMeals(String value)
	{
		
		Assert.assertTrue(addMealButton.isDisplayed());
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(addMealButton)).click();
	
		driver.switchTo().frame("modal_window");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		List<WebElement> excessBaggageList = driver.findElements(By.xpath(".//li[@class='relative']/a/span[1]"));
		
		try
		{
		excessBaggageList.stream().filter(x -> x.getText().contains(value)).findFirst().ifPresent(ele -> ele.click());	
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement doneButton = driver.findElement(By.xpath(".//div[@class='col col4']/input"));
	
		try 
		{	
			if(doneButton.isEnabled())
			{
				doneButton.click();	
			}
			
		}catch(Exception e)
		{
				e.printStackTrace();
		}	
		
		driver.switchTo().defaultContent();
		
	}
	
	public void Enable_Insurance(String value)
	{
		
		boolean insuranceStatus = insuranceEnableCheckBox.isSelected();
		
		if(value.equalsIgnoreCase("YES") && insuranceStatus == false)
		{
			
			insuranceEnableCheckBox.click();
		}
		else if(value.equalsIgnoreCase("NO") && insuranceStatus == true)
		{
			
			insuranceEnableCheckBox.click();
		}
	}
	
	public void selectSeat() throws InterruptedException
	{
	
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.visibilityOf(selectSeatButton)).click();
	/*	
		String windowCurrent = driver.getWindowHandle();
		System.out.println(windowCurrent);*/
		
		//driver.switchTo().frame(driver.findElement(By.xpath("(//iframe)[2]")));
		
		
		/*Set<String>  windowsAll = driver.getWindowHandles();		
		
		for (String str : windowsAll)
		{
			System.out.println(str);
			if(!str.equals(windowCurrent))
			{
				driver.switchTo().window(str);
				System.out.println("Swicting to Woindow");
			}
		}*/
		

		
		selectSeatButton.click();
		
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;

		int count=0;
		for (int i=2; i<=31; i++)
		{
			for (int j=1; j<=6; j++)
			{
					try 
					{
						WebDriverWait wait = new WebDriverWait(driver, 10);
						WebElement seat = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(".//table[@id='flightSeatMap']/tbody/tr/td["+ i +"]/a["+ j +"]"))));
						javaScriptExecutor.executeScript("arguments[0].click();",seat);
					count=1;
						if((seat.getCssValue("color").equals("rgba(51, 51, 51, 1)")))
						{
							break;
						}
						
						
					}catch(Exception e)
					{
						System.out.println();
					}
					
			}
					
			if(count == 1)	
			{
				break;
			}
		
			
		}
				
		javaScriptExecutor.executeScript("$('#confirmSeat').trigger('click');");
}
		

	public void acceptTC_ContinueBooking()
	{
		if(insuranceConfirmCheckBox.isSelected() == false)
		insuranceConfirmCheckBox.click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		itineraryContinueButton.click();
		
		
	}
}
