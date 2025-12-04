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

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final String URL_SERVER_APPIUM = "http://127.0.0.1:4723/wd/hub";
    private static final ReadJson readJson = new ReadJson();

    private DriverFactory() {
    }

    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            try {
                if (getPlatformName().equalsIgnoreCase("android")) {
                    log.info("Conectando ao device \"" + getDeviceName() + "\"");

                    // APK file
                    File apkFile = new File("src/test/resources/apk/android.wdio.native.app.v1.0.8.apk");

                    // Configurações do driver
                    AndroidDriver androidDriver = new AndroidDriver(new URL(URL_SERVER_APPIUM), getOptions(apkFile));

                    String appPackage = readJson.getCapabilities().get("appPackage").toString();

                    if (!androidDriver.isAppInstalled(appPackage)) {
                        log.info("App não instalado. Instalando APK...");
                        androidDriver.installApp(apkFile.getAbsolutePath());
                    }

                    androidDriver.activateApp(appPackage);

                    driver.set(androidDriver);
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao conectar ao device \"" + getDeviceName() + "\": " + e.getMessage(), e);
            }
        }
        return driver.get();
    }

    private static UiAutomator2Options getOptions(File apkFile) {
        String appPackage = readJson.getCapabilities().get("appPackage").toString();
        String appActivity = readJson.getCapabilities().get("appActivity").toString();

        var options = new UiAutomator2Options()
                .setPlatformName(getPlatformName())
                .setPlatformVersion(readJson.getCapabilities().get("platformVersion").toString())
                .setUdid(getUdid())
                .setDeviceName(getDeviceName())
                .setAutomationName(readJson.getCapabilities().get("automationName").toString())
                .setApp(apkFile.getAbsolutePath())
                .setAppPackage(appPackage)
                .setAppActivity(appActivity)
                .setAutoGrantPermissions(true)
                .setAppWaitDuration(Duration.ofSeconds(30))
                .setNoReset(false);
        return options;
    }

    public static String getUdid() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("udid");
    }

    public static String getPlatformName() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platformName");
    }

    public static String getDeviceName() {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName");
    }

    public static void quitDriver() {
        AppiumDriver drv = driver.get();
        if (drv != null) {
            try {
                drv.quit();
            } catch (Exception e) {
                log.warn("Erro ao fechar driver: {}", e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
}
