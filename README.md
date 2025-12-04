📱 Automação Mobile – Desafio Técnico

Automação mobile Android estruturada em Page → Logic → Step, usando Appium 2.x, Java 11, Maven, Cucumber e TestNG.
Projeto feito para demonstrar domínio de arquitetura limpa, boas práticas e automação mobile sólida.

🚀 Tecnologias

Java 11

Maven

Appium 2.x (UiAutomator2)

Cucumber JVM

TestNG

PageFactory

Allure Reports

Lombok

SLF4J + Logback

🧱 Arquitetura do Projeto
automation-mobile-challenge
├── allure-results
├── src
│    ├── main/java/com.automation.mobile
│    │      ├── enums
│    │      ├── home
│    │      │      ├── page
│    │      │      ├── logic
│    │      │      └── step
│    │      ├── sign_up
│    │      │      ├── page
│    │      │      ├── logic
│    │      │      └── step
│    │      ├── swipe
│    │      │      ├── page
│    │      │      ├── logic
│    │      │      └── step
│    │      └── utils
│    └── test
│         ├── java/com.automation.mobile/RunnerMobileTest.java
│         └── resources
│               ├── apk
│               ├── device-config/android-device-config.json
│               ├── features
│               ├── fixtures
│               └── suites/suite_all_testes.xml
├── pom.xml
└── README.md

🧩 Padrão Page → Logic → Step
📄 Page

Apenas localizadores (@AndroidFindBy)

Sem lógica ou fluxo de teste

Exemplo: SwipePage.java

🧠 Logic

Regras de navegação

Comportamentos (scroll, tap, waits)

Operações de negócio (login, swipe)

Exemplo: SwipeLogic.java

🎙 Step

Liga Cucumber → Logic

Sem regra de negócio ou elementos

Exemplo: SwipeStep.java

Benefício: Step clean, Logic testável, Page estável.

▶️ Como Rodar
# Build do projeto
mvn clean install

# Executar todos os testes
mvn test

# Executar suite TestNG
mvn test -DsuiteXmlFile=src/test/resources/suites/suite_all_testes.xml

📁 Suite TestNG

Arquivo: src/test/resources/suites/suite_all_testes.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Suite Test All">
    <test name="Suite Test All">
        <!-- Parâmetros do dispositivo -->
        <parameter name="udid" value="emulator-5554" />
        <parameter name="platformName" value="android" />
        <parameter name="deviceName" value="Pixel" />

        <classes>
            <class name="com.automation.mobile.RunnerMobileTest" />
        </classes>
    </test>
</suite>


Explicação:

<suite> → Conjunto de testes.

<test> → Grupo de testes dentro da suite.

<parameter> → Parâmetros do dispositivo que podem ser lidos no Runner (@Parameters).

<classes> → Classes que contêm os testes (Runner Cucumber + TestNG).

TestNG injeta os parâmetros no Runner e dispara os testes no dispositivo ou emulador configurado.

📱 Appium Setup
npm install -g appium
appium driver install uiautomator2
appium


Configuração do dispositivo:
src/test/resources/device-config/android-device-config.json

📁 Fixtures

Local: src/test/resources/fixtures
Usado para mensagens esperadas, textos e payloads.
No mobile, substitui o environment.yaml das APIs.

🧪 Runner Cucumber + TestNG

Arquivo: src/test/java/com/automation/mobile/RunnerMobileTest.java

Responsável por:

Carregar features

Configurar plugins Allure

Associar glue (steps)

Rodar com TestNG Platform via suite XML

🔧 Utils

DriverFactory – criação do driver

UtilsMobile – waits, visibility, clicks

Scroll.java – gestures W3C (PointerInput + Sequence)

AutomationException

Gerador de evidências

Config loaders

Exemplo scroll:

Scroll.scroll(ScrollDirection.DOWN, elementoScroll, elementoTarget);

📊 Allure Reports

Gerado em /allure-results

Visualizar:

allure serve allure-results

### 🖼️ Prints do relatório

![Allure Report](screenshots-allure-reports/allure-01.png)
![Allure Report](screenshots-allure-reports/allure-02.png)

📜 Features (Cucumber BDD)

Exemplo: 

Feature: Cadastro de Usuário

Scenario: CT01 - Realizar cadastro de usuário
Given que acesso a home do app
When preencho campo e-mail
And preencho o campo senha
And preencho o campo de confirmacao de senha
Then deve exibir mensagem de sucesso de cadastro


Steps → chamam Logic

Logic → toca na tela / faz scroll

Page → só os elementos

❌ iOS

Não implementado:

Sem Mac / Xcode / WDA

Fora do escopo do desafio

❌ CI / Pipeline Mobile

Não incluso, GitHub Actions não suporta emulador Android estável

Mobile farm fora do desafio

Foco na execução local

✨ Conclusão

O projeto demonstra:

Arquitetura limpa: Page → Logic → Step

Scroll e gestures modernos (W3C)

Uso correto do Appium 2

Testes organizados e modulares

BDD sem gambiarra

Allure integrado

Config externa de device

Java 11 + Maven + TestNG

Projeto fácil de rodar e entender

✍️ Desenvolvido por

Lucas Pereira Valentim
Automação • Mobile • Qualidade • Café ☕💻