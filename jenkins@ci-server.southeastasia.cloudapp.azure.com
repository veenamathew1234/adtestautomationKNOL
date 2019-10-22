[
  {
    "line": 1,
    "elements": [
      {
        "line": 3,
        "name": "Successful Login",
        "description": "After user enters valid credentials he should be landed on Kompass home page",
        "id": "a.login;successful-login",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 2243547657,
              "status": "passed"
            },
            "line": 6,
            "name": "User is already in login page",
            "match": {
              "location": "loginPageDefinition.user_is_already_in_login_page()"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 7361891,
              "status": "passed"
            },
            "line": 7,
            "name": "Title of the page is Login to AktivLearn Plus",
            "match": {
              "location": "loginPageDefinition.title_of_the_page_is_Login_to_AktivLearnPlus()"
            },
            "keyword": "When "
          },
          {
            "result": {
              "duration": 270593172,
              "status": "passed"
            },
            "line": 8,
            "name": "User enters email and password",
            "match": {
              "location": "loginPageDefinition.user_enters_email_and_password()"
            },
            "keyword": "Then "
          },
          {
            "result": {
              "duration": 2079725987,
              "status": "passed"
            },
            "line": 9,
            "name": "User clicks on signin button",
            "match": {
              "location": "loginPageDefinition.user_clicks_on_signin_button()"
            },
            "keyword": "Then "
          }
        ]
      }
    ],
    "name": "A.Login",
    "description": "",
    "id": "a.login",
    "keyword": "Feature",
    "uri": "src/main/java/Features/ALogin.feature"
  },
  {
    "line": 1,
    "elements": [
      {
        "line": 3,
        "name": "Skip demographics page",
        "description": "",
        "id": "b.demographics;skip-demographics-page",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 7115392,
              "status": "passed"
            },
            "line": 5,
            "name": "The user has landed on the demographics page",
            "match": {
              "location": "demographicsPageDefinition.the_user_has_landed_on_the_demographics_page()"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 8222139104,
              "status": "passed"
            },
            "line": 6,
            "name": "fill the demographics page and policy page",
            "match": {
              "location": "demographicsPageDefinition.fill_the_demographics_page_and_policy_page()"
            },
            "keyword": "Then "
          }
        ]
      }
    ],
    "name": "B.Demographics",
    "description": "",
    "id": "b.demographics",
    "keyword": "Feature",
    "uri": "src/main/java/Features/BDemographics.feature"
  },
  {
    "line": 1,
    "elements": [
      {
        "line": 2,
        "name": "To validate Welcome Page",
        "description": "",
        "id": "c.welcome-page;to-validate-welcome-page",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 2278759880,
              "status": "passed"
            },
            "line": 4,
            "name": "User is on welcome Page",
            "match": {
              "location": "welcomePageDefinition.user_is_on_welcome_Page()"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 1098323348,
              "status": "passed"
            },
            "line": 5,
            "name": "User clicks on Accepts Invitation button",
            "match": {
              "location": "welcomePageDefinition.user_clicks_on_Accepts_Invitation_button()"
            },
            "keyword": "Then "
          }
        ]
      }
    ],
    "name": "C.Welcome Page",
    "description": "",
    "id": "c.welcome-page",
    "keyword": "Feature",
    "uri": "src/main/java/Features/CWelcome.feature"
  },
  {
    "line": 1,
    "elements": [
      {
        "line": 3,
        "name": "Validate journey page",
        "description": "",
        "id": "d.journeypage;validate-journey-page",
        "type": "scenario",
        "keyword": "Scenario",
        "steps": [
          {
            "result": {
              "duration": 587291434,
              "status": "passed"
            },
            "line": 5,
            "name": "User is already in journey page",
            "match": {
              "location": "journeyPageDefinition.user_is_already_in_journey_page()"
            },
            "keyword": "Given "
          },
          {
            "result": {
              "duration": 153589130662,
              "status": "passed"
            },
            "line": 6,
            "name": "User able to navigate through each phase",
            "match": {
              "location": "journeyPageDefinition.user_able_to_navigate_through_each_phase()"
            },
            "keyword": "Then "
          }
        ]
      }
    ],
    "name": "D.JourneyPage",
    "description": "",
    "id": "d.journeypage",
    "keyword": "Feature",
    "uri": "src/main/java/Features/DJourney.feature"
  }
]