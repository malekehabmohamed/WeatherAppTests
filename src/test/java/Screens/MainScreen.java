package Screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MainScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By settingDoneButton = AppiumBy.id("com.info.weather.forecast:id/tv_button_done");
    private final By privacyPolicy = AppiumBy.id("com.info.weather.forecast:id/ll_got_it");
    private final By temperatureUnit = AppiumBy.id("com.info.weather.forecast:id/tv_current_temper_unit");
    private final By tvDateId = AppiumBy.id("com.info.weather.forecast:id/tv_date");

    private final By timeFormatDropDownId = AppiumBy.id("com.graph.weather.forecast.channel:id/tg_format_time_setting");
    private final By timeFormat24Hour = AppiumBy.xpath("//android.widget.FrameLayout[@index='2']//android.widget.TextView[@index='1']");
    private final By timeFormatSelectedSetting = AppiumBy.id("com.info.weather.forecast:id/tv_timeformat_setting");

    private final By tempUnitDropDownId = AppiumBy.id("com.graph.weather.forecast.channel:id/tgTemperature_setting");
    private final By tempUnitCelsius = AppiumBy.xpath("//android.widget.FrameLayout[@index='2']//android.widget.TextView[@index='0']");
    private final By tempSelectedSetting = AppiumBy.id("com.info.weather.forecast:id/tv_temp_setting");

    private final By navigationSetting = AppiumBy.id("com.info.weather.forecast:id/iv_bt_navigation_setting");
    private final By unitSettingId = AppiumBy.id("com.info.weather.forecast:id/ll_item_unit_setting");

    private final By hourlyInfoList = AppiumBy.id("com.info.weather.forecast:id/ll_hourly_info_list");
    private final By hoursList = AppiumBy.xpath("//android.widget.TextView[@resource-id='com.info.weather.forecast:id/tv_hour_item']");
    private final By rainList = AppiumBy.xpath("//android.widget.TextView[@resource-id='com.info.weather.forecast:id/tv_rain_chance']");
    private final By humidityList = AppiumBy.xpath("//android.widget.TextView[@resource-id='com.info.weather.forecast:id/tv_humidity']");

    private final By TemperatureToggle= AppiumBy.id("com.graph.weather.forecast.channel:id/tgTemperature_setting");
    private final By TimeFormatToggle= AppiumBy.id("com.graph.weather.forecast.channel:id/tg_format_time_setting");





    public MainScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(50));
    }

    public void getMainScreen() {
        driver.findElement(settingDoneButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(privacyPolicy)).click();
    }

    public void openNavigationSettings() {
        driver.findElement(navigationSetting).click();
    }

    public void selectUnitSettings() {
        driver.findElement(unitSettingId).click();
    }

    public void changeTimeFormatTo12hours() {
        wait.until(ExpectedConditions.elementToBeClickable(TimeFormatToggle)).click();
        driver.findElement(TimeFormatToggle).click();
    }

    public void changeTempUnitToFahrenheit() {
        wait.until(ExpectedConditions.elementToBeClickable(TemperatureToggle)).click();
        driver.findElement(TemperatureToggle).click();
    }

    public void changeTempUnitToCelsius() {
        wait.until(ExpectedConditions.elementToBeClickable(tempUnitDropDownId)).click();
        driver.findElement(tempUnitCelsius).click();
        assertSetting("C", tempSelectedSetting);
    }

    public void rainAndHumidityValues(int hours) {
        List<WebElement> times = driver.findElements(hoursList);
        List<WebElement> rains = driver.findElements(rainList);
        List<WebElement> humidities = driver.findElements(humidityList);

        for (int i = 0; i < hours && i < times.size(); i++) {
            String time = times.get(i).getText();
            String rain = rains.get(i).getText();
            String humidity = humidities.get(i + 1).getText();

            Assert.assertNotNull(time);
            System.out.println(time);

            Assert.assertNotNull(rain);
            System.out.println(rain);

            Assert.assertNotNull(humidity);
            System.out.println(humidity);
        }

        swipe();
    }

    public void swipe() {
        WebElement hourlyInfoListElement = driver.findElement(hourlyInfoList);
        int x = hourlyInfoListElement.getLocation().getX();
        int y = hourlyInfoListElement.getLocation().getY();
        performSwipe(x, y);
    }

    private void performSwipe(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x + 50, y));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    public void assert12HoursFormat() {
        Assert.assertTrue(assertTimeFormatContains("AM") || assertTimeFormatContains("PM"));
    }

    public void assert24HoursFormat() {
        Assert.assertFalse(assertTimeFormatContains("AM") || assertTimeFormatContains("PM"));
    }

    public void assertTempUnitInCelsius() {
        assertTempUnitEquals("°C");
    }

    public void assertTempUnitInFahrenheit() {
        assertTempUnitEquals("°F");
    }

    private void assertSetting(String expected, By locator) {
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        Assert.assertEquals(actual, expected);
        driver.findElement(settingDoneButton).click();
    }

    private boolean assertTimeFormatContains(String expected) {
        String tvDate = wait.until(ExpectedConditions.visibilityOfElementLocated(tvDateId)).getText();
        return tvDate.contains(expected);
    }

    private void assertTempUnitEquals(String expected) {
        String tempUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(temperatureUnit)).getText();
        Assert.assertEquals(tempUnit, expected);
    }
}
