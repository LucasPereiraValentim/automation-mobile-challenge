Feature: Cadastro de Usuário

  Scenario: CT01 - Realizar cadastro de usuário
    Given que acesso a home do app
    When preencho campo e-mail
    And preencho o campo senha
    And preencho o campo de confirmacao de senha
    Then deve exibir mensagem de sucesso de cadastro