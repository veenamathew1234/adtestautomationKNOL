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
  "duration": 217796643,
  "error_message": "org.picocontainer.injectors.AbstractInjector$UnsatisfiableDependenciesException: org.openqa.selenium.remote.RemoteWebDriver has unsatisfied dependency \u0027interface org.openqa.selenium.Capabilities\u0027 for constructor \u0027public org.openqa.selenium.remote.RemoteWebDriver(org.openqa.selenium.Capabilities)\u0027 from org.picocontainer.DefaultPicoContainer@e383572:6\u003c|\n\tat org.picocontainer.injectors.ConstructorInjector.getGreediestSatisfiableConstructor(ConstructorInjector.java:191)\n\tat org.picocontainer.injectors.ConstructorInjector.getGreediestSatisfiableConstructor(ConstructorInjector.java:110)\n\tat org.picocontainer.injectors.ConstructorInjector.access$100(ConstructorInjector.java:51)\n\tat org.picocontainer.injectors.ConstructorInjector$1.run(ConstructorInjector.java:331)\n\tat org.picocontainer.injectors.AbstractInjector$ThreadLocalCyclicDependencyGuard.observe(AbstractInjector.java:270)\n\tat org.picocontainer.injectors.ConstructorInjector.getComponentInstance(ConstructorInjector.java:364)\n\tat org.picocontainer.injectors.AbstractInjectionFactory$LifecycleAdapter.getComponentInstance(AbstractInjectionFactory.java:56)\n\tat org.picocontainer.behaviors.AbstractBehavior.getComponentInstance(AbstractBehavior.java:64)\n\tat org.picocontainer.behaviors.Stored.getComponentInstance(Stored.java:91)\n\tat org.picocontainer.DefaultPicoContainer.getInstance(DefaultPicoContainer.java:699)\n\tat org.picocontainer.DefaultPicoContainer.getComponent(DefaultPicoContainer.java:647)\n\tat org.picocontainer.DefaultPicoContainer.getComponent(DefaultPicoContainer.java:632)\n\tat org.picocontainer.parameters.BasicComponentParameter$1.resolveInstance(BasicComponentParameter.java:118)\n\tat org.picocontainer.parameters.ComponentParameter$1.resolveInstance(ComponentParameter.java:136)\n\tat org.picocontainer.injectors.SingleMemberInjector.getParameter(SingleMemberInjector.java:78)\n\tat org.picocontainer.injectors.ConstructorInjector$CtorAndAdapters.getParameterArguments(ConstructorInjector.java:309)\n\tat org.picocontainer.injectors.ConstructorInjector$1.run(ConstructorInjector.java:335)\n\tat org.picocontainer.injectors.AbstractInjector$ThreadLocalCyclicDependencyGuard.observe(AbstractInjector.java:270)\n\tat org.picocontainer.injectors.ConstructorInjector.getComponentInstance(ConstructorInjector.java:364)\n\tat org.picocontainer.injectors.AbstractInjectionFactory$LifecycleAdapter.getComponentInstance(AbstractInjectionFactory.java:56)\n\tat org.picocontainer.behaviors.AbstractBehavior.getComponentInstance(AbstractBehavior.java:64)\n\tat org.picocontainer.behaviors.Stored.getComponentInstance(Stored.java:91)\n\tat org.picocontainer.DefaultPicoContainer.getInstance(DefaultPicoContainer.java:699)\n\tat org.picocontainer.DefaultPicoContainer.getComponent(DefaultPicoContainer.java:647)\n\tat org.picocontainer.DefaultPicoContainer.getComponent(DefaultPicoContainer.java:678)\n\tat cucumber.runtime.java.picocontainer.PicoFactory.getInstance(PicoFactory.java:40)\n\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:38)\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:300)\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:44)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)\n\tat org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:678)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)\n\tat org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)\n\tat ✽.Given User is already in login page(features/Login.feature:5)\n",
  "status": "failed"
});
formatter.match({
  "location": "login.title_of_the_page_is_Login_to_Kompass()"
});
formatter.result({
  "status": "skipped"
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