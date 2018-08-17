Feature: Cleartrip Flight Reservation 
	
Meta:
product:search

@First
Scenario: One Way Flight Reservation 

Given User is on Home Page and selects Flights Menu
When User selects Flights Enters data and search
Then Matching flights should be displayed
And User should be able to add Flight Details




