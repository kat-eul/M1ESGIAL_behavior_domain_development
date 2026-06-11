package dojo.banque;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class VirementSteps {
    Map<Integer, Account> accounts = new HashMap<>();
    Account emetteur;
    Account beneficiaire;
    Integer amount;
    String result;
    TransferService transferService = new TransferService();

    @Given("the following initial state of accounts:")
    public void the_following_initial_state_of_accounts(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            Integer id = Integer.parseInt(row.get("account_id"));
            String owner = row.get("owner");
            AccountType type = AccountType.valueOf(row.get("type"));
            Integer balance = Integer.parseInt(row.get("balance"));
            accounts.put(id, new Account(id, owner, type, balance));
        });
    }

    @When("{int} initiates a transfer to {int} with an amount of {int} euros")
    public void initiates_a_transfer_to_with_an_amount_of_euros(Integer int1, Integer int2, Integer amount) {
        emetteur = accounts.get(int1);
        beneficiaire = accounts.get(int2);
        this.amount = amount;
        result = transferService.transferTo(emetteur, beneficiaire, amount);
    }

    @Then("the transfer is {string}")
    public void the_transfer_is(String string) {
        assert(result.equals(string));
    }

    @Then("the final balance of the emitter is {int} euros")
    public void the_final_balance_of_the_emitter_is_euros(Integer int1) {
        assert(emetteur.balance.equals(int1));
    }

    @Then("the final balance of the beneficiary is {int} euros")
    public void the_final_balance_of_the_beneficiary_is_euros(Integer int1) {
        assert(beneficiaire.balance.equals(int1));
    }
}
