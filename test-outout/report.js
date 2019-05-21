$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/Login.feature");
formatter.feature({
  "line": 1,
  "name": "Login",
  "description": "",
  "id": "login",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 2,
  "name": "Successful Login",
  "description": "After user enters valid credentials he should be landed on Kompass home page",
  "id": "login;successful-login",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "User is already in login page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Title of the page is Login to Kompass",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "User enters email and password",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "User clicks on signin button",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "User is on Kompass home page",
  "keyword": "Then "
});
formatter.match({
  "location": "login.user_is_already_in_login_page()"
});
formatter.result({
  "duration": 1990841009,
  "status": "passed"
});
formatter.match({
  "location": "login.title_of_the_page_is_Login_to_Kompass()"
});
formatter.result({
  "duration": 715679,
  "error_message": "java.lang.NullPointerException\n\tat stepDefinitions.login.title_of_the_page_is_Login_to_Kompass(login.java:47)\n\tat ✽.When Title of the page is Login to Kompass(features/Login.feature:6)\n",
  "status": "failed"
});
formatter.match({
  "location": "login.user_enters_email_and_password()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});