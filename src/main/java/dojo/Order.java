package dojo;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String from;
    private String to;
    private String message;
    private List<String> contents = new ArrayList<String>();

    public void declareOwner(String romeo) {
        this.from = romeo;
    }

    public void declareTarget(String juliette) {
        this.to = juliette;
    }

    public void declareMessage(String message) {
        this.message = message;
    }

    public List<String> getCocktails() {
        return contents;
    }

    public String getTicket() {
        return "From "+this.from+ " to "+this.to+ ": "+this.message;
    }
}