Feature: Virement entre deux comptes

  En tant que client de banque, je souhaite pouvoir effectuer un virement entre deux comptes.

  Background:
    Given the following initial state of accounts:
      |account_id| owner | type     | balance |
      |1 | Alice          | COURANT   | 100     |
      |2 | Alice          | EPARGNE   | 200     |
      |3 | Bob            | COURANT   | 150     |
      |4 | Bob            | EPARGNE   | 250     |

  Scenario Outline: Possibilité de faire un virement entre deux comptes en respectant les règles concernant les types de comptes.
    When <emetteur_id> initiates a transfer to <bénéficiaire_id> with an amount of 0 euros
    Then the transfer is "<result>"

    Examples:
      |emetteur_id| bénéficiaire_id | result  |
      |1          | 3               | possible |
      |1          | 2               | possible |
      |2          | 1               | possible |
      |1          | 4               | not_possible |

  Scenario Outline: Le virement n'est exécuté que si le solde du compte émetteur, après déduction du montant et des frais, reste supérieur ou égal à zéro.

    When <emetteur_id> initiates a transfer to <bénéficiaire_id> with an amount of <amount> euros
    Then the transfer is "<result>"
    And the final balance of the emitter is <final_balance> euros
    And the final balance of the beneficiary is <final_beneficiary_balance> euros

    Examples:
      |emetteur_id| bénéficiaire_id |amount | result       | final_balance | final_beneficiary_balance |
      |1          | 3               |50     | possible     | 49            | 250                       |
      |1          | 2               |50     | possible     | 49            | 200                       |
      |2          | 1               |50     | possible     | 150           | 150                       |
      |2          | 1               |500    | not_possible | 150           | 200                       |
      |1          | 4               |50     | not_possible | 100           | 250                       |