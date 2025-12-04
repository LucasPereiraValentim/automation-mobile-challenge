# 📱 Automação Mobile
Desafio TécnicoEste projeto apresenta uma solução de automação mobile para Android seguindo uma arquitetura limpa e robusta.
A estrutura adota o padrão Page → Logic → Step, promovendo a modularidade, reusabilidade e manutenibilidade dos testes.O objetivo é demonstrar domínio de arquitetura de testes sólida, boas práticas de codificação e o uso eficiente do Appium 2.x.
### 🚀 Tecnologias Utilizadas
A automação foi desenvolvida utilizando as seguintes tecnologias:Java 11: Linguagem principal do projeto.  
Maven: Ferramenta de automação de build.  
Appium 2.x (UiAutomator2): Framework de automação mobile.  
Cucumber JVM: Para a implementação do BDD (Behavior-Driven Development).  
TestNG: Framework de testes utilizado para rodar o Cucumber e gerenciar parâmetros de dispositivos.  
PageFactory: Padrão para inicialização de elementos de página.  
Allure Reports: Geração de relatórios de teste detalhados e visuais.  
Lombok: Para reduzir o boilerplate de código Java.  
SLF4J + Logback: Para gerenciamento eficiente de logs.  
JavaFaker: Para geração de dados dinâmicos e fakes para realizar os testes
 
 # 🧱 Arquitetura do Projeto
 O projeto é estruturado em pacotes que refletem o padrão Page → Logic → Step e separam claramente as responsabilidades.
 
```
├── allure-results
├── src
│    ├── main/java/com.automation.mobile
│    │      ├── enums              # Tipos enumerados
│    │      ├── home
│    │      │      ├── page         # Localizadores de elementos da Home
│    │      │      ├── logic        # Regras de negócio/navegação da Home
│    │      │      └── step         # Implementação Cucumber BDD da Home
│    │      ├── sign_up            # Pacote de Cadastro (Page, Logic, Step)
│    │      ├── swipe              # Pacote de Gestos Swipe (Page, Logic, Step)
│    │      └── utils              # Classes de utilidade e suporte
│    └── test
│         ├── java/com.automation.mobile/RunnerMobileTest.java # Runner Principal
│         └── resources
│               ├── apk            # Arquivo .apk do aplicativo
│               ├── device-config/android-device-config.json # Configuração do dispositivo
│               ├── features       # Arquivos .feature do Cucumber
│               ├── fixtures       # Dados de teste (mensagens, payloads)
│               └── suites/suite_all_testes.xml # Suite TestNG
├── pom.xml
└── README.md
```
🧩 Padrão Page → Logic → Step
Este padrão é o coração da arquitetura, garantindo a separação de preocupações (Separation of Concerns).
📄 Page: Armazena apenas localizadores de elementos.,@AndroidFindBy (sem lógica/fluxo).,"Elementos estáveis, isolados de regras."
🧠 Logic: Contém a regra de negócio e a navegação.,"Comportamentos (scroll, tap, waits) e operações (login, swipe).","Lógica testável, reutilizável e isolada do Step."
🎙 Step: Liga o Cucumber à camada Logic.,Chamadas de métodos da Logic (sem regras de negócio ou elementos).,"Steps clean (focados no BDD), fácil leitura."

## 🌐 Configuração Nessárias

--------------------------------------------------

1. 🌐 Configurando Variáveis de Ambiente no Windows (Interface)
   1️⃣ Abrir as Configurações de Variáveis de Ambiente

Abra o Menu Iniciar e digite Editar as variáveis de ambiente do sistema.

Clique na opção que aparecer.

Na janela Propriedades do Sistema, clique no botão Variáveis de Ambiente....

2️⃣ Configurar JAVA_HOME

Na seção Variáveis do sistema, clique em Novo....

No campo Nome da variável, digite:

```
JAVA_HOME
```

No campo Valor da variável, coloque o caminho da instalação do Java 11, por exemplo:

```
C:\Program Files\Java\jdk-11.0.20
```

Clique em OK para salvar.

3️⃣ Configurar MAVEN_HOME

Ainda em Variáveis do sistema, clique em Novo....

Nome da variável:

```
MAVEN_HOME
```

Valor da variável: caminho da pasta do Maven, por exemplo:

```
C:\apache-maven-3.9.5
```

Clique em OK.

4️⃣ Configurar ALLURE_HOME

Clique em Novo... novamente.

Nome da variável:
```
ALLURE_HOME
```
Valor da variável: caminho da pasta do Allure CLI, por exemplo:

```
C:\allure-2.35.1
```
Clique em OK.

5️⃣ Atualizar a variável PATH

Na seção Variáveis do sistema, localize a variável Path e clique em Editar....

Clique em Novo e adicione os seguintes caminhos (ajuste conforme suas instalações):

```

%JAVA_HOME%\bin

%MAVEN_HOME%\bin

%ALLURE_HOME%\bin

```

Clique em OK em todas as janelas para salvar.

6️⃣ Testar as configurações

Abra o Prompt de Comando (cmd).

Execute:

```

java -version

mvn -version

allure --version

```

▶️ Como Rodar

Pré-requisitos

Instalar o Appium via CMD
```
npm i -g appium
```
```
appium driver install uiautomator2
```
Iniciar servidor
```
appium --address 127.0.0.1 --port 9000 --base-path /wd/hub
```
Configurar Dispositivo/Emulador 
Android: O projeto espera que um emulador ou dispositivo real esteja ativo. 

A configuração é definida em:src/test/resources/device-config/android-device-config.json

Execução dos Testes

O projeto utiliza o TestNG para injetar os parâmetros do dispositivo e rodar os testes.

Comandos:
```
mvn clean install
mvn test -DsuiteXmlFile=src/test/resources/suites/suite_all_testes.xmlRecomendado: 
```
Executa todos os testes via Suite do TestNG.
📁 Suite TestNG
O arquivo de suíte (src/test/resources/suites/suite_all_testes.xml) permite configurar o dispositivo de forma externa:

```
XML<suite name="Suite Test All">
    <test name="Suite Test All">
        <parameter name="udid" value="emulator-5554" />
        <parameter name="platformName" value="android" />
        <parameter name="deviceName" value="Pixel" />

        <classes>
            <class name="com.automation.mobile.RunnerMobileTest" />
        </classes>
    </test>
</suite>
```
# 🛠️ Detalhes da Implementação

## 🧪 Runner Cucumber + TestNGArquivo: src/test/java/com/automation/mobile/RunnerMobileTest.java
Função: É o ponto de entrada. Responsável por carregar features, configurar plugins Allure e associar os steps (glue). É rodado pelo TestNG para receber os parâmetros de dispositivo.

## 🔧 Utilitários (/utils)
Contém classes de suporte essenciais: 
DriverFactory: Criação e gerenciamento do driver.UtilsMobile: 
Métodos comuns de espera (waits), visibilidade e cliques.
Scroll.java: Implementação de gestures modernos (scroll) usando a especificação W3C (PointerInput + Sequence),
Outros: Gerador de evidências, Config loaders e tratamento de exceções (AutomationException).

## 📁 Fixtures: src/test/resources/fixtures
Uso: Armazenamento de dados de teste esperados, como mensagens de sucesso, textos.
## 📜 Features (Cucumber BDD)
As features descrevem o comportamento de forma legível

## 📊 Allure Reports
Relatórios de teste são gerados automaticamente na pasta /allure-results.
Para visualizar o relatório, execute o comando (requer o CLI do Allure instalado)
```
allure serve allure-results
```

## ⛔ Exclusões e Limitações

#### iOS❌ Não ImplementadoRequer Mac/Xcode/WDA

#### Pipeline ❌ Não Incluso

#### ✅ Foco na execução local

## 📝 Screenshots capturadas do relatório gerado pelo AllureReports

![AllureReports](https://github.com/LucasPereiraValentim/automation-mobile-challenge/blob/master/src/test/screenshots-allure-reports/allure-01.png)

![AllureReports](https://github.com/LucasPereiraValentim/automation-mobile-challenge/blob/master/src/test/screenshots-allure-reports/allure-02.png)

Desenvolvido por Lucas Pereira Valentim • Automação • Mobile • Qualidade • Café ☕💻
