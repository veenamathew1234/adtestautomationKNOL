$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Features/ALogin.feature");
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
  "duration": 6466961029,
  "status": "passed"
});
formatter.match({
  "location": "loginPageDefinition.title_of_the_page_is_Login_to_AktivLearnPlus()"
});
formatter.result({
  "duration": 20709794,
  "status": "passed"
});
formatter.match({
  "location": "loginPageDefinition.user_enters_email_and_password()"
});
formatter.result({
  "duration": 968990644,
  "status": "passed"
});
formatter.match({
  "location": "loginPageDefinition.user_clicks_on_signin_button()"
});
formatter.result({
  "duration": 1955405122,
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
  "duration": 36076543,
  "status": "passed"
});
formatter.match({
  "location": "demographicsPageDefinition.refresh_the_demographics_page_by_redirecting_again_to_the_same_url()"
});
formatter.result({
  "duration": 4995389075,
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
  "keyword": "Then "
});
formatter.match({
  "location": "welcomePageDefinition.user_is_on_welcome_Page()"
});
formatter.result({
  "duration": 76776651,
  "status": "passed"
});
formatter.match({
  "location": "welcomePageDefinition.user_clicks_on_Accepts_Invitation_button()"
});
formatter.result({
  "duration": 756630,
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
  "name": "User clicks on each of the phases",
  "keyword": "Then "
});
formatter.match({
  "location": "journeyPageDefinition.user_is_already_in_journey_page()"
});
formatter.result({
  "duration": 9114277439,
  "status": "passed"
});
formatter.match({
  "location": "journeyPageDefinition.user_clicks_on_each_of_the_phases()"
});
formatter.result({
  "duration": 78502182157,
  "status": "passed"
});
});