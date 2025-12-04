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

    public static void validationTestByVisible(WebElement element, String testName, String description) {
        log.info("Validando visibilidade: {} - {}", testName, description);
        GeneratorEvidence.logStep(description);

        boolean isVisible = element.isDisplayed();
        log.info("Resultado {}: {}", testName, isVisible ? "SUCESSO" : "FALHOU");
        assertTrue(isVisible, "Elemento visível: " + element);

    }

    public static void validationTestByText(WebElement element, String testName, String description, String expectedText) {
        log.info("Validando texto: {} - {}", testName, description);
        GeneratorEvidence.logStep(description);

        boolean isTextCorrect = getTextSafe(element).equals(expectedText);
        log.info("Resultado {}: {}", testName, isTextCorrect ? "SUCESSO" : "FALHOU");
        assertTrue(isTextCorrect, "Texto esperado: '" + expectedText + "', encontrado: '" + element.getText() + "'");

    }

    public static void validationTestByText(List<WebElement> elements, String testName, String description, List<String> expectedTexts) {
        log.info("Validando textos: {} - {}", testName, description);
        GeneratorEvidence.logStep(description);

        if (elements.size() != expectedTexts.size()) {
            throw new IllegalArgumentException("Tamanho de elements (" + elements.size() + ") difere do tamanho de expectedTexts (" + expectedTexts.size() + ")");
        }

        for (int i = 0; i < elements.size(); i++) {
            String actual = getTextSafe(elements.get(i));
            String expected = expectedTexts.get(i);
            boolean ok = expected.equals(actual);
            log.info("Resultado {} [{}]: {}", testName, i, ok ? "SUCESSO" : "FALHOU");
            assertTrue(ok, "Elemento[" + i + "] - texto esperado: '" + expected + "', encontrado: '" + actual + "'");
        }
    }

    private static String getTextSafe(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            log.warn("Falha ao obter texto do elemento: {}", e.toString());
            return "";
        }
    }

}

