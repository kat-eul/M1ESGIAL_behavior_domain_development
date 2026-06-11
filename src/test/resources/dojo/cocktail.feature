Feature: Cocktail Ordering

  As Romeo, I want to offer a drink to Juliette so that we can discuss together (and maybe more).

  Background:
    Given "Michel" who wants to buy a drink

  Scenario Outline: Creating an empty order
    When an order is declared for "<target>"
    Then there is <number> cocktails in the order

    Examples:
      | owner | target   | number |
      | Romeo | Juliette |      0 |
      | Tom   | Jerry    |      0 |

  Scenario Outline: Sending a message with an order
    Given "<from>" who wants to buy a drink
    When an order is declared for "<to>"
    And a message saying "<message>" is added
    Then the ticket must say "From <from> to <to>: <message>"
    And there is <number> cocktails in the order

    Examples:
      | from  | to       | message     | number |
      | Romeo | Juliette | Wanna chat? |      0 |
      | Tom   | Jerry    | Hei!        |      0 |