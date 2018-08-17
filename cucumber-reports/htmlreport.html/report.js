$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("TestCases.feature");
formatter.feature({
  "line": 1,
  "name": "Cleartrip Flight Reservation",
  "description": "\r\nMeta:\r\nproduct:search",
  "id": "cleartrip-flight-reservation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 9966819565,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "One Way Flight Reservation",
  "description": "",
  "id": "cleartrip-flight-reservation;one-way-flight-reservation",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "User is on Home Page and selects Flights Menu",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "User selects Flights Enters data and search",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "Matching flights should be displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "User should be able to add Flight Details",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinition.User_is_on_Home_Page()"
});
formatter.result({
  "duration": 2740708482,
  "status": "passed"
});
formatter.match({
  "location": "stepDefinition.User_selects_Flights_Enters_data_and_search()"
});
formatter.result({
  "duration": 2027236401,
  "status": "passed"
});
formatter.match({
  "location": "stepDefinition.Matching_flights_should_be_displaye()"
});
formatter.result({
  "duration": 16468308063,
  "status": "passed"
});
formatter.match({
  "location": "stepDefinition.User_should_be_able_to_add_Flight_Details()"
});
formatter.result({
  "duration": 7318573705,
  "status": "passed"
});
});