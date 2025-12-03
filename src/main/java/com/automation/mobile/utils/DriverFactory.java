package com.automation.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;

import java.io.File;
import java.net.URL;
import java.time.Duration;

@Slf4j
public class DriverFactory {

    private static AppiumDriver instance;

    private DriverFactory() {}

    private final static String URL_SERVER_APPIUM = "http://127.0.0.1:4723/wd/hub";

    public static AppiumDriver getDriver() {
        try {
            if (instance == null) {
                if (getPlatformName().equalsIgnoreCase("android")) {
                    log.info("Conectando ao device \"" + getDeviceName() + "\"");
                    AndroidDriver driver = new AndroidDriver(new URL(URL_SERVER_APPIUM), getOptions());

                    // Instala o APK se não estiver no device
                    File apkFile = new File("src/test/resources/apk/android.wdio.native.app.v1.0.8.apk");
                    if (!driver.isAppInstalled("com.wdiodemoapp")) {
                        log.info("App não instalado. Instalando APK...");
                        driver.installApp(apkFile.getAbsolutePath());
                    }

                    driver.activateApp("com.wdiodemoapp"); // garante que o app está aberto
                    instance = driver;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao device \"" + getDeviceName() + "\": " + e.getMessage(), e);
        }
        return instance;
    }

    private static UiAutomator2Options getOptions() {
        ReadJson readJson = new ReadJson();
        return new UiAutomator2Options()
                .setPlatformName(getPlatformName())
                .setPlatformVersion(readJson.getCapabilities().get("platformVersion").toString())
                .setUdid(getUdid())
                .setDeviceName(getDeviceName())
                .setAppPackage(readJson.getCapabilities().get("appPackage").toString())
                .setAppActivity(readJson.getCapabilities().get("appActivity").toString())
                .setAutomationName(readJson.getCapabilities().get("automationName").toString())
                .setAutoGrantPermissions(true)
                .setAppWaitDuration(Duration.ofSeconds(30));
    }

    private static String getUdid() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("udid");
    }

    public static String getPlatformName() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platformName");
    }

    private static String getDeviceName() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName");
    }
}
