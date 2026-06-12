package dojo.banque;

import java.util.Objects;

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
        // Un virement est possible si le compte bénéficiaire est de type COURANT (tous propriétaires)
        // ou si le bénéficiaire est de type EPARGNE et appartient au même propriétaire que l'émetteur.
        boolean isTypeAccountPossible = beneficiaire.type == AccountType.COURANT ||
                (beneficiaire.type == AccountType.EPARGNE && Objects.equals(emetteur.owner, beneficiaire.owner));
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
