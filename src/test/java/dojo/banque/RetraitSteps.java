package dojo.banque;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

public class RetraitSteps {

    Account account;
    Distributeur distributeur;
    DistributeurService distributeurService = new DistributeurService();
    String result;

    @Before
    public void setup() {
        account = null;
        distributeur = null;
        result = null;
    }

    @Given("Alice who has a bank account with a balance of {int} euros")
    public void alice_who_has_a_bank_account_with_a_balance_of_euros(Integer int1) {
        account = new Account(1,"Alice",AccountType.COURANT,int1);
    }

    @Given("a cash machine with a reserve of {int} euros")
    public void a_cash_machine_with_a_reserve_of_euros(Integer reserve) {
        distributeur = new Distributeur();
        distributeur.balance = reserve;
    }

    @When("Alice tries to withdraw {int} euros")
    public void alice_tries_to_withdraw_euros(Integer int1) {
        result = distributeurService.retraitFrom(account, distributeur, int1);
    }

    @Then("the withdrawal is {string}")
    public void the_withdrawal_is(String string) {
        assert(result.equals(string));
    }

    @Then("the balance of Alice's account is {int} euros")
    public void the_balance_of_alice_s_account_is_euros(Integer finalBalance) {
        assert(account.balance.equals(finalBalance));
    }

    @Then("the reserve of the cash machine is {int} euros")
    public void the_reserve_of_the_cash_machine_is_euros(Integer finalReserve) {
        assert(distributeur.balance.equals(finalReserve));
    }
}
