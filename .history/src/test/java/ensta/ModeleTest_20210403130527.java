package ensta;

import com.ensta.librarymanager.utils.*;

import com.ensta.librarymanager.model.*;

/**
 * ModeleTest
 */
public class ModeleTest {

    public static void main(String[] args) {
        Membre membre = new Membre(1, "Dupont", "Alain", "Paris", "123@gmail.com", 
        "123456", Abonnement.BASIC);
        System.out.println("membre: "+membre);

        Livre livre = new Livre(1, "Les oiseaux migrateurs", "Patrick FICHTER", "978-2817704876");
        System.out.println("livre "+livre);

        Emprunt emprunt = new Emprunt(1, livre, membre, "2020-12-01", "2021-01-05");
        System.out.println("Emprunt "+emprunt);
    }
}