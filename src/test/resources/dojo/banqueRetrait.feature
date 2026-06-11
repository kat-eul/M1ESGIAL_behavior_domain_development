Feature: Retrait au distributeur

  En tant que client de banque, je souhaite pouvoir retirer de l'argent à un distributeur afin de disposer de liquidités.

  Scenario Outline: Succès ou échec d'un retrait selon le solde du compte.
    Given Alice who has a bank account with a balance of 100 euros
    And a cash machine with a reserve of 1000 euros
    When Alice tries to withdraw <amount> euros
    Then the withdrawal is "<result>"

    Examples:
        | amount | result  |
        | 50     | success |
        | 150    | failure |
        | 50     | success |
        | 60     | failure |


  Scenario Outline: Succès ou échec d'un retrait selon la réserve du distributeur.
    Given Alice who has a bank account with a balance of 1000 euros
    And a cash machine with a reserve of <reserve> euros
    When Alice tries to withdraw 200 euros
    Then the withdrawal is "<result>"

    Examples:
        | reserve | result  |
        | 1000    | success |
        | 150     | failure |
        | 200     | success |
        | 199     | failure |

  Scenario: Règles de gestion d'un retrait réussi
    Given Alice who has a bank account with a balance of <balance> euros
    And a cash machine with a reserve of <reserve> euros
    When Alice tries to withdraw <amount> euros
    Then the withdrawal is "success"
    And the balance of Alice's account is <final_balance> euros
    And the reserve of the cash machine is <final_reserve> euros

    Example:
        | balance | reserve | amount | final_balance | final_reserve |
        | 100     | 1000    | 50     | 50            | 950           |
        | 200     | 1000    | 100    | 100           | 900           |