package dojo.banque;

public class TransferService {

    public String transferTo(Account emetteur, Account beneficiaire, Integer amount){
        String transferIsPossible = transferIsPossible(emetteur, beneficiaire, amount);
        if(transferIsPossible.equals("possible")){
            emetteur.balance -= amount;
            if(emetteur.type == AccountType.COURANT){
                emetteur.balance -= 1;
            }
            beneficiaire.balance += amount;
        }
        return transferIsPossible;
    }

    private String transferIsPossible(Account emetteur, Account beneficiaire, Integer amount){
        //Vérification du type du compte
        boolean isTypeAccountPossible = emetteur.type == AccountType.COURANT ||
                (emetteur.type == AccountType.EPARGNE && emetteur.owner == beneficiaire.owner);
        //Vérification du solde avec frais
        boolean isSoldeSufficiant = false;
        if(emetteur.type == AccountType.COURANT){
            isSoldeSufficiant = emetteur.balance >= amount+1;
        }else{
            isSoldeSufficiant = emetteur.balance >= amount;
        }

        return isTypeAccountPossible && isSoldeSufficiant ? "possible" : "not_possible";
    }
}
