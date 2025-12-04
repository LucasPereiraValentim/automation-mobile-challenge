package com.automation.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;

@Slf4j
public class UtilsMobile {

    public static AppiumDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public static void quitDriver() {
        DriverFactory.quitDriver();
    }

    private static void screenshot(String descricao) {
        GeneratorEvidence.logStep(descricao);
    }

    public static void waitElementToVisibility(WebElement element, String descricao) {
        log.info("Esperando elemento ficar visível...");
        new WebDriverWait(getDriver(), Duration.ofSeconds(40)).until(ExpectedConditions.visibilityOf(element));
        screenshot(descricao);
    }

    public static void waitElementToBeClickable(WebElement element, String descricao) {
        log.info(descricao);
        new WebDriverWait(getDriver(), Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
        screenshot(descricao);
    }

    public static void click(WebElement element, String descricao) {
        waitElementToBeClickable(element, "Esperando antes de clicar");
        log.info("Clicando no elemento...");
        element.click();
        screenshot(descricao);
    }

    public static void sendKeys(WebElement element, String texto, String descricao) {
        waitElementToBeClickable(element, "Esperando antes de digitar");
        log.info("Digitando texto: {}", texto);
        element.sendKeys(texto);
        screenshot(descricao);
    }

    public static void clearInput(WebElement element, String descricao) {
        waitElementToBeClickable(element, "Esperando antes de limpar");
        log.info("Limpando campo...");
        element.clear();
        screenshot(descricao);
    }

    public static void backPage(String descricao) {
        log.info("Voltando página...");
        getDriver().navigate().back();
        screenshot(descricao);
    }

    public static boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (RuntimeException e) {
            log.warn("Elemento não está disponível: {}", e.getMessage());
            return false;
        }
    }

}