# Automated Test Scenarios for Weather Forecast App

## Overview
This repository contains automated test scenarios written in Java using Appium for testing the Weather Forecast app. The tests focus on verifying the functionality related to temperature units, time formats, and weather data retrieval.

## Prerequisites
- Appium should be installed. [Appium Installation Guide](http://appium.io/docs/en/about-appium/getting-started/)

## Setup
1. Clone the repository to your local machine.
2. Ensure that the required dependencies are installed, such as the Appium server and the necessary Appium Java client.
3. Set up the Appium server with the desired capabilities in the `setUp` method of the `testScenarios` class.
4. Adjust the file path in the `appium:app` capability to the location of the Weather Forecast app APK on your machine.

## Test Scenarios
The test scenarios cover the following:

### Scenario: Get Main Screen with 12-Hour Format and Fahrenheit Unit
- Change temperature unit to Fahrenheit.
- Change time format to 12 hours.
- Retrieve the main screen.
- Verify the temperature unit is in Fahrenheit.
- Verify the time format is in 12 hours.
- Check and assert rain and humidity values for a specified number of hours.

## Running the Tests
Execute the `testScenarios` class as a TestNG test to run the automated scenarios.

## Notes
- The `hours` variable in the `testScenarios` class is set to 6 by default. Adjust it as needed for your test cases.

## Author
Malek Ehab
Senior QC Engineer
