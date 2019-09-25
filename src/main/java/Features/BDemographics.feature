Feature: B.Demographics

Scenario: Skip demographics page

Given The user has landed on the demographics page
Then Refresh the demographics page by redirecting again to the same url 
