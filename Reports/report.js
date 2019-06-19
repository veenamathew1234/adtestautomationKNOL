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
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 5544733474,
=======
  "duration": 5011039463,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
  "status": "passed"
=======
  "duration": 3564011199,
  "error_message": "java.lang.NullPointerException\n\tat pageRepository.loginPage.launchPage(loginPage.java:39)\n\tat stepDefinitions.loginPageDefinition.user_is_already_in_login_page(loginPageDefinition.java:27)\n\tat ✽.Given User is already in login page(Features/ALogin.feature:6)\n",
  "status": "failed"
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
});
formatter.match({
  "location": "loginPageDefinition.title_of_the_page_is_Login_to_AktivLearnPlus()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 15505721,
=======
  "duration": 14695256,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
  "status": "passed"
=======
  "status": "skipped"
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
});
formatter.match({
  "location": "loginPageDefinition.user_enters_email_and_password()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 718145091,
=======
  "duration": 515117729,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
  "status": "passed"
=======
  "status": "skipped"
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
});
formatter.match({
  "location": "loginPageDefinition.user_clicks_on_signin_button()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 1487270644,
=======
  "duration": 1212833174,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
  "status": "passed"
=======
  "status": "skipped"
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
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
<<<<<<< HEAD
  "duration": 70435573,
=======
  "duration": 48866720,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
=======
  "duration": 442172268,
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
  "status": "passed"
});
formatter.match({
  "location": "demographicsPageDefinition.refresh_the_demographics_page_by_redirecting_again_to_the_same_url()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 6041029975,
=======
  "duration": 2567551902,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
=======
  "duration": 5043141567,
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
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
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 41629647,
=======
  "duration": 52087144,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
=======
  "duration": 55179766,
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
  "status": "passed"
});
formatter.match({
  "location": "welcomePageDefinition.user_clicks_on_Accepts_Invitation_button()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 341844,
=======
  "duration": 138942,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
=======
  "duration": 135323,
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
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
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 12087205136,
=======
  "duration": 9063248442,
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
=======
  "duration": 12035773926,
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
  "status": "passed"
});
formatter.match({
  "location": "journeyPageDefinition.user_clicks_on_each_of_the_phases()"
});
formatter.result({
<<<<<<< HEAD
<<<<<<< HEAD
  "duration": 22881416914,
  "error_message": "org.openqa.selenium.WebDriverException: unknown error: Element \u003cdiv class\u003d\"_1d26jyp\"\u003e...\u003c/div\u003e is not clickable at point (1568, 174). Other element would receive the click: \u003cdiv role\u003d\"alert\" class\u003d\"Toastify__toast-body\"\u003e...\u003c/div\u003e\n  (Session info: chrome\u003d64.0.3282.140)\n  (Driver info: chromedriver\u003d2.33.506092 (733a02544d189eeb751fe0d7ddca79a0ee28cce4),platform\u003dLinux 4.4.0-141-generic x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nBuild info: version: \u00273.7.0\u0027, revision: \u00272321c73\u0027, time: \u00272017-11-02T22:22:35.584Z\u0027\nSystem info: host: \u0027KNOLSKAPE-L31\u0027, ip: \u0027127.0.1.1\u0027, os.name: \u0027Linux\u0027, os.arch: \u0027amd64\u0027, os.version: \u00274.4.0-141-generic\u0027, java.version: \u00271.8.0_161\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptSslCerts: true, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.33.506092 (733a02544d189e..., userDataDir: /tmp/.org.chromium.Chromium...}, cssSelectorsEnabled: true, databaseEnabled: false, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: LINUX, platformName: LINUX, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 64.0.3282.140, webStorageEnabled: true}\nSession ID: d5cc9f968090b50635ea29f7f4c62df4\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:82)\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:45)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:164)\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:586)\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:279)\n\tat org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:83)\n\tat pageRepository.journeyPage.logout(journeyPage.java:356)\n\tat pageRepository.journeyPage.navigateThroughPhases(journeyPage.java:95)\n\tat stepDefinitions.journeyPageDefinition.user_clicks_on_each_of_the_phases(journeyPageDefinition.java:21)\n\tat ✽.Then User clicks on each of the phases(Features/DJourney.feature:6)\n",
=======
  "duration": 24448859940,
  "error_message": "org.openqa.selenium.WebDriverException: unknown error: Element \u003cdiv class\u003d\"_1d26jyp\"\u003e...\u003c/div\u003e is not clickable at point (1568, 174). Other element would receive the click: \u003cdiv role\u003d\"alert\" class\u003d\"Toastify__toast-body\"\u003e...\u003c/div\u003e\n  (Session info: chrome\u003d64.0.3282.186)\n  (Driver info: chromedriver\u003d2.33.506092 (733a02544d189eeb751fe0d7ddca79a0ee28cce4),platform\u003dLinux 4.15.0-51-generic x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nBuild info: version: \u00273.7.0\u0027, revision: \u00272321c73\u0027, time: \u00272017-11-02T22:22:35.584Z\u0027\nSystem info: host: \u0027KNOLSKAPE-L102\u0027, ip: \u0027127.0.1.1\u0027, os.name: \u0027Linux\u0027, os.arch: \u0027amd64\u0027, os.version: \u00274.15.0-51-generic\u0027, java.version: \u00271.8.0_161\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptSslCerts: true, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.33.506092 (733a02544d189e..., userDataDir: /tmp/.org.chromium.Chromium...}, cssSelectorsEnabled: true, databaseEnabled: false, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: LINUX, platformName: LINUX, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 64.0.3282.186, webStorageEnabled: true}\nSession ID: 42a66556ab60e2cdf96275ba2911d3c6\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:82)\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:45)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:164)\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:586)\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:279)\n\tat org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:83)\n\tat pageRepository.journeyPage.logout(journeyPage.java:365)\n\tat pageRepository.journeyPage.navigateThroughPhases(journeyPage.java:95)\n\tat stepDefinitions.journeyPageDefinition.user_clicks_on_each_of_the_phases(journeyPageDefinition.java:21)\n\tat ✽.Then User clicks on each of the phases(Features/DJourney.feature:6)\n",
>>>>>>> 7587e8945c2930cd0900c1627bafccad7849087d
=======
  "duration": 109916383,
  "error_message": "org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {\"method\":\"xpath\",\"selector\":\"//div[contains(@class,\u0027header-module-individual-item-container\u0027)][3]\"}\n  (Session info: chrome\u003d64.0.3282.140)\n  (Driver info: chromedriver\u003d2.33.506092 (733a02544d189eeb751fe0d7ddca79a0ee28cce4),platform\u003dLinux 4.4.0-141-generic x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.7.0\u0027, revision: \u00272321c73\u0027, time: \u00272017-11-02T22:22:35.584Z\u0027\nSystem info: host: \u0027KNOLSKAPE-L31\u0027, ip: \u0027127.0.1.1\u0027, os.name: \u0027Linux\u0027, os.arch: \u0027amd64\u0027, os.version: \u00274.4.0-141-generic\u0027, java.version: \u00271.8.0_161\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptSslCerts: true, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.33.506092 (733a02544d189e..., userDataDir: /tmp/.org.chromium.Chromium...}, cssSelectorsEnabled: true, databaseEnabled: false, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: LINUX, platformName: LINUX, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 64.0.3282.140, webStorageEnabled: true}\nSession ID: a1f7993a120e6e6cebddb9bcfd6127de\n*** Element info: {Using\u003dxpath, value\u003d//div[contains(@class,\u0027header-module-individual-item-container\u0027)][3]}\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:82)\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:45)\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:164)\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:586)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:356)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:458)\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:361)\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:348)\n\tat pageRepository.journeyPage.logout(journeyPage.java:361)\n\tat pageRepository.journeyPage.navigateThroughPhases(journeyPage.java:100)\n\tat stepDefinitions.journeyPageDefinition.user_clicks_on_each_of_the_phases(journeyPageDefinition.java:21)\n\tat ✽.Then User clicks on each of the phases(Features/DJourney.feature:6)\n",
>>>>>>> 3eaba0d9073a05e1f0386ba6afa00ce04d441480
  "status": "failed"
});
});