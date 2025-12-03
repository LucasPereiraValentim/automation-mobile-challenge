package com.automation.mobile.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertTrue;

@Slf4j
public class Assertion {

    public static void validationTestByVisible(List<WebElement> elements, String testName, String description) {
        log.info("Validando visibilidade: {} - {}", testName, description);
        GeneratorEvidence.logStep(description);

        for (WebElement element : elements) {
            boolean isVisible = element.isDisplayed();
            log.info("Resultado {}: {}", testName, isVisible ? "SUCESSO" : "FALHOU");
            assertTrue(isVisible, "Elemento não visível: " + element);
        }
    }

    public static void validationTestByText(List<WebElement> elements, String testName, String description, String expectedText) {
        log.info("Validando texto: {} - {}", testName, description);
        GeneratorEvidence.logStep(description);

        for (WebElement element : elements) {
            boolean isTextCorrect = element.getText().equals(expectedText);
            log.info("Resultado {}: {}", testName, isTextCorrect ? "SUCESSO" : "FALHOU");
            assertTrue(isTextCorrect, "Texto esperado: '" + expectedText + "', encontrado: '" + element.getText() + "'");
        }
    }
}

