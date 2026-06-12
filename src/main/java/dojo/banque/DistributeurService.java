package dojo.banque;

public class DistributeurService {

    public String retraitFrom(Account owner, Distributeur distributeur, Integer amount){
        boolean soldeOk = owner.balance >= amount;
        boolean reserveOk = distributeur.balance >= amount;

        if (soldeOk && reserveOk) {
            owner.balance -= amount;
            distributeur.balance -= amount;
            return "success";
        }
        return "failure";
    }
}
