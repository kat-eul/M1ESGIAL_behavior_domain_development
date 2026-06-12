package dojo.banque;

public class Account {
    public Integer account_id;
    public String owner;
    public AccountType type;
    public Integer balance;

    public Account(Integer id, String owner, AccountType type, Integer balance) {
        this.account_id = id;
        this.owner = owner;
        this.type = type;
        this.balance = balance;
    }
}

