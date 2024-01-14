package TestCases;

import Screens.MainScreen;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;


public class testScenarios{

    private AppiumDriver driver;
    private MainScreen mainScreen;
    private int hours = 6;


    @BeforeTest
    public void setUp() throws MalformedURLException {
        URL serverUrl = new URL("http://0.0.0.0:4723");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:app", "D:\\com_graph_weather_forecast_channel_82_65101848_27293a482a4f7da6.apk");
        caps.setCapability("autoGrantPermissions", "true");

        driver = new AndroidDriver(serverUrl, caps);
    }

    @Test
    public void getMainScreenWith12HoursFormatAndFahrenheitUnit(){
        mainScreen.changeTempUnitToFahrenheit();
        mainScreen.changeTimeFormatTo12hours();
        mainScreen.getMainScreen();
        mainScreen.assertTempUnitInFahrenheit();
        mainScreen.assert12HoursFormat();
        mainScreen.rainAndHumidityValues(hours);
    }

}
