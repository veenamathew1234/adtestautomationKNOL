Feature: Login

@ignore
Scenario: Successful Login
After user enters valid credentials he should be landed on Kompass home page

Given User is already in login page
When Title of the page is Login to Kompass
Then User enters email and password
Then User clicks on signin button
Then User is on Kompass home page