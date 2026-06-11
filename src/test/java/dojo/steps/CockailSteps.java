package dojo.steps;

import dojo.Order;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CockailSteps {

    Order order;

    @Given("{string} who wants to buy a drink")
    public void who_wants_to_buy_a_drink(String owner){
        order=new Order();
        order.declareOwner(owner);
    }

    @When("an order is declared for {string}")
    public void an_order_is_declared_for(String target){
        order.declareTarget(target);
    }

    @When("a message saying {string} is added")
    public void a_message_saying_is_added(String message){
        order.declareMessage(message);
    }

    @Then("there is {int} cocktails in the order")
    public void there_is_cocktails_in_the_order(int number) {
        List<String> cocktails=order.getCocktails();
        assertEquals(number,cocktails.size());
    }

    @Then("the ticket must say {string}")
    public void the_ticket_must_say_expectedMessage(String expectedMessage) {
        String ticket = order.getTicket();
        assertEquals(expectedMessage, ticket);
    }
}
