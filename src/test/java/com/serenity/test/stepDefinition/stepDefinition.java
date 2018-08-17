package com.serenity.test.stepDefinition;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Listeners;

import com.serenity.test.pages.HomePage;
import com.serenity.test.pages.LoginPage;
import com.serenity.test.pages.ReviewDetailsPage;
import com.serenity.test.pages.SearchResultsPage;

import TestBase.ExtentReportListener;
import TestBase.ITestExtent;
import TestBase.TestBase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class stepDefinition extends TestBase
{
	
	HomePage homePage;
	SearchResultsPage Listpage; 
	ReviewDetailsPage reviewDetailsPage;
	LoginPage loginPage;
	
	HashMap<String, String> baseMap;
	
	
	@Before()
	public void setUp() throws IOException
	{	
		TestBase.initializeBrowser();
		baseMap = (HashMap<String, String>) TestBase.readFromExcel("C:\\EclipseWorkspace\\FlightReservationCucumberListener\\src\\test\\resources\\testData\\Scenario1.xlsx", "Scenario 1");
		loginPage = new LoginPage(driver);
		loginPage.signIn(repository.getProperty("Username"), repository.getProperty("Password"));
		
	}
	
	@After
	public void tearDow()
	{
		driver.quit();
	}

	
	@Given("User is on Home Page and selects Flights Menu")
	public void User_is_on_Home_Page() throws InterruptedException
	{
		homePage = new HomePage(driver);
		
		homePage.checkFlightsOption();

	}
	
	@When("User selects Flights Enters data and search")
	public void User_selects_Flights_Enters_data_and_search()
	{
	
		homePage = new HomePage(driver);
		
		Assert.assertTrue(homePage.getHomeTitle().contains("Search flights"));
		homePage.select_TripType(baseMap.get("tripType"));
		homePage.enter_FromCity(baseMap.get("fromCity"));
		homePage.enter_ToCity(baseMap.get("toCity"));
		homePage.enter_DepartDate(baseMap.get("departDate"));
		homePage.enter_Adults(1);
		homePage.enter_Adults(1);
		homePage.click_SearchButton();
	}
	
	@Then("Matching flights should be displayed")
	public void Matching_flights_should_be_displaye() throws InterruptedException
	{
		Listpage = new SearchResultsPage(driver);
		
		Listpage.bookFlight(baseMap.get("flightTime"));
	}
	
	@And("User should be able to add Flight Details")
	public void User_should_be_able_to_add_Flight_Details() throws InterruptedException
	{
	
		reviewDetailsPage = new ReviewDetailsPage(driver);
		
		Assert.assertTrue(reviewDetailsPage.check_Itinerary());
		reviewDetailsPage.addBaggage(baseMap.get("baggage"));			
		reviewDetailsPage.addMeals(baseMap.get("mealType"));
		reviewDetailsPage.selectSeat();
		reviewDetailsPage.Enable_Insurance(baseMap.get("insurance"));
		reviewDetailsPage.acceptTC_ContinueBooking();
	}
}
