<<<<<<< HEAD
$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Features/Demographics.feature");
formatter.feature({
  "line": 1,
  "name": "Demographics",
  "description": "",
  "id": "demographics",
  "keyword": "Feature"
});
formatter.uri("Features/Login.feature");
=======
$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Features/ALogin.feature");
>>>>>>> shalini
formatter.feature({
  "line": 1,
  "name": "Login",
  "description": "",
  "id": "login",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Successful Login",
  "description": "After user enters valid credentials he should be landed on Kompass home page",
  "id": "login;successful-login",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "User is already in login page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Title of the page is Login to AktivLearn Plus",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "User enters email and password",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "User clicks on signin button",
  "keyword": "Then "
});
formatter.match({
  "location": "loginPageDefinition.user_is_already_in_login_page()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 6372757994,
=======
  "duration": 4867164230,
>>>>>>> shalini
=======
  "duration": 5794204118,
>>>>>>> shalini
=======
  "duration": 11301977772,
>>>>>>> shalini
  "status": "passed"
});
formatter.match({
  "location": "loginPageDefinition.title_of_the_page_is_Login_to_AktivLearnPlus()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 30806581,
=======
  "duration": 18788090,
>>>>>>> shalini
=======
  "duration": 14384881,
>>>>>>> shalini
=======
  "duration": 19482712,
>>>>>>> shalini
  "status": "passed"
});
formatter.match({
  "location": "loginPageDefinition.user_enters_email_and_password()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 549147572,
=======
  "duration": 642340357,
>>>>>>> shalini
=======
  "duration": 640892999,
>>>>>>> shalini
=======
  "duration": 581584592,
>>>>>>> shalini
  "status": "passed"
});
formatter.match({
  "location": "loginPageDefinition.user_clicks_on_signin_button()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 1357466548,
=======
  "duration": 1363407871,
>>>>>>> shalini
=======
  "duration": 1815279432,
=======
  "duration": 1401423762,
>>>>>>> shalini
  "status": "passed"
});
formatter.uri("Features/BDemographics.feature");
formatter.feature({
  "line": 1,
  "name": "Demographics",
  "description": "",
  "id": "demographics",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Skip demographics page",
  "description": "",
  "id": "demographics;skip-demographics-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "The user has landed on the demographics page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Refresh the demographics page by redirecting again to the same url",
  "keyword": "Then "
});
formatter.match({
  "location": "demographicsPageDefinition.the_user_has_landed_on_the_demographics_page()"
});
formatter.result({
<<<<<<< HEAD
  "duration": 22201994,
>>>>>>> shalini
=======
  "duration": 28405004,
>>>>>>> shalini
  "status": "passed"
});
formatter.match({
  "location": "demographicsPageDefinition.refresh_the_demographics_page_by_redirecting_again_to_the_same_url()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 4754044862,
=======
  "duration": 2936891575,
>>>>>>> shalini
=======
  "duration": 5165713733,
>>>>>>> shalini
=======
  "duration": 3735224882,
>>>>>>> shalini
  "status": "passed"
});
formatter.uri("Features/CWelcome.feature");
formatter.feature({
  "line": 1,
  "name": "Welcome Page",
  "description": "",
  "id": "welcome-page",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 2,
  "name": "To validate Welcome Page",
  "description": "",
  "id": "welcome-page;to-validate-welcome-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User is on welcome Page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "User clicks on Accepts Invitation button",
  "keyword": "When "
});
formatter.match({
  "location": "welcomePageDefinition.user_is_on_welcome_Page()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 14954289,
=======
  "duration": 29939182,
>>>>>>> shalini
=======
  "duration": 33788831,
>>>>>>> shalini
=======
  "duration": 26211904,
>>>>>>> shalini
  "status": "passed"
});
formatter.match({
  "location": "welcomePageDefinition.user_clicks_on_Accepts_Invitation_button()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 5083437583,
  "status": "passed"
=======
  "duration": 7027736994,
=======
  "duration": 108076,
>>>>>>> shalini
=======
  "duration": 167023,
>>>>>>> shalini
  "status": "passed"
});
formatter.uri("Features/DJourney.feature");
formatter.feature({
  "line": 1,
  "name": "JourneyPage",
  "description": "",
  "id": "journeypage",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Validate journey page",
  "description": "",
  "id": "journeypage;validate-journey-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User is already in journey page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "User clicks on each of the phase items",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "User should be displayed with correct page",
  "keyword": "Then "
});
formatter.match({
  "location": "journeyPageDefinition.user_is_already_in_journey_page()"
});
formatter.result({
  "duration": 7051184440,
  "status": "passed"
});
formatter.match({
  "location": "journeyPageDefinition.user_clicks_on_each_of_the_phase_items()"
});
formatter.result({
  "duration": 29798618433,
  "status": "passed"
});
formatter.match({
  "location": "journeyPageDefinition.user_should_be_displayed_with_correct_page()"
});
formatter.result({
  "duration": 65581,
  "status": "passed"
});
<<<<<<< HEAD
formatter.uri("Features/Demographics.feature");
formatter.feature({
  "line": 1,
  "name": "Demographics",
  "description": "",
  "id": "demographics",
  "keyword": "Feature"
>>>>>>> shalini
});
=======
>>>>>>> shalini
});