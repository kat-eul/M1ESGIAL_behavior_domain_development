package dojo.banque;

public class DistributeurService {

    public String retraitFrom(Account owner, Integer amount){
            if(owner.type == AccountType.COURANT && owner.balance >= amount+1){
                owner.balance -= amount+1;
                return "possible";
            }else if(owner.type == AccountType.EPARGNE && owner.balance >= amount){
                owner.balance -= amount;
                return "possible";
            }
            return "not_possible";
    }
}
