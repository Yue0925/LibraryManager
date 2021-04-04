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
        System.out.println(membre);
    }
}