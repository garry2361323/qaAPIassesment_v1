# Project Summary

This framework uses TestNG with RestAssured for testing.

* "output/<env>/" contains all the log generated during test execution.
* "output/" contains extent report i.e Test_Report.html
* "src/main/java/com/freenow/qa/api" contains classes required for executing specific APIs
* "src/main/java/com/freenow/qa/listeners" contains testng listner classes for generating report, logs and failed test case retrial mechanism.
* "src/main/java/com/freenow/qa/pojo" contains POJO classes required for serialization and deserialization.
* "src/main/java/com/freenow/qa/schema""contains json schema definitions (http://json-schema.org/draft-07/schema) for each API response.
* "src/main/java/com/freenow/qa/testdata" contains test data and helper classes.
* "src/main/java/com/freenow/qa/util" contains all the utility functions used in project
* "src/test/java/com/freenow/qa/tests" contains all the Test Cases.
* "src/test/resources/testrunner" contains testng.xml file
* "src/test/resources" contains all the environment specific configuration settings, log4j properties file and extent config.

## How To Run :
### Pre-requisites - Adding environment variables

* Under "src/test/resources/common.properties" specify environment i.e. QA or UAT
* Under "src/test/resources/config.json" specify API baseURI and endpoints for QA and UAT

Method-1 - Running Via IDE
* Go to "src/test/resources/testrunner"
* Right click on testng.xml > Run As> TestNG Test to run smoke, negative, regression and end to end scenarios.
* Right click on testngEnd2End.xml > Run As> TestNG Test to run only end to end scenarios.

Method-2 - Running Via Command Line
* Open "Command Prompt"
* Goto Project directory
* Type following command
  ```
  java org.testng.TestNG testng1.xml
  ```
* You also execute test cases based on groups
	```
	java org.testng.TestNG testng1.xml -groups "smoke,regression"
	```

## 	Running in parallel :
* When running tests in parallel, supply following additional mandatory attributes
	```
	java org.testng.TestNG testng1.xml -groups "smoke,regression" -parallel classes
	```

###	Run Report
Test Reports can be found at path \output\Test_Report.html (Automatically created after first run)
![Image description]()
