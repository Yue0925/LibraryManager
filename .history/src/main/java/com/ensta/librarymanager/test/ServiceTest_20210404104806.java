package com.ensta.librarymanager.service;

import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;
import com.ensta.librarymanager.utils.*;

/**
 * ServiceTest
 */
public class ServiceTest {

    public static void main(String[] args) {
        FillDatabase.main(args);
        MembreService membreService = MembreServiceImpl.getInstance();
        LivreService livreService = LivreServiceImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();

        try {
            // test membre
            int id_membre = membreService.create("membre1", "Membre1", "ensta", "membre1@ensta-paris.fr",
            "06000000");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}