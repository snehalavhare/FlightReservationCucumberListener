package com.serenity.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import TestBase.TestBase;

@Component
public class LoginPage extends TestBase {
	

	@FindBy(xpath = "//*[@id='userAccountLink']/span[2]")
	WebElement yourTripsField;
	
	@FindBy(xpath = ".//*[@id = 'userAccountMenu']/li[1]/ul/li[1]/input")
	WebElement signInField;
	
	@FindBy(id = "modal_window")
	WebElement signFrame;
	
	@FindBy(xpath = ".//*[@id='signinForm']/dl/dd[1]/input")
	WebElement textUsername;
	
	@FindBy(xpath = ".//*[@id='signinForm']/dl/dd[3]/input")
	WebElement textPassword;
	
	@FindBy(xpath = ".//*[@id='signinForm']/dl/dd[5]/button")
	WebElement signInButton;
	
	@FindBy(id="global_signout")
	WebElement signOutButon;
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectYoutTrips()
	{
		
		yourTripsField.click();
	}
	
	public void clickSignInField()
	{
		signInField.click();
	}
	
	public void enterUserName(String value)
	{
		textUsername.sendKeys(value);
	}
	
	public void enterPassword(String value)
	{
		textPassword.sendKeys(value);
	}
	
	public void clickSignIn()
	{
		signInButton.click();
	}
	
	public void clickSignOut()
	{
		signOutButon.click();
	}
	
	public void signIn(String username, String password)
	{
		
		this.selectYoutTrips();
		this.clickSignInField();
		
		driver.switchTo().frame(signFrame);
		
		this.enterUserName(username);
		this.enterPassword(password);
		this.clickSignIn();
	}


}
