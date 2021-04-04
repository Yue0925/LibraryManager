package com.ensta.librarymanager.service;

import java.time.LocalDate;

import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.model.*;

/**
 * ServiceTest
 */
public class ServiceTest {

    public static void main(String[] args) throws Exception {
        try {
            FillDatabase.main(args);
            MembreService membreService = MembreServiceImpl.getInstance();
            LivreService livreService = LivreServiceImpl.getInstance();
            EmpruntService empruntService = EmpruntServiceImpl.getInstance();

            // test creations
            int id_membre = membreService.create("membre1", "Membre1", "ensta", "membre1@ensta-paris.fr",
            "06000000");
            int id_livre = livreService.create("Harry Potter", "J. K. Rowling",  "2070541274");
            empruntService.create(id_membre, id_livre, LocalDate.now());

            // test updates
            Membre membre = membreService.getById(id_membre);
            membre.setAdresse("New Adresse");
            membreService.update(membre);

            // test disponibilités
            System.out.println("Disponibiliés for the book " + id_livre + " : " +
             empruntService.isLivreDispo(id_livre));
            System.out.println("Disponibilitées for the member " + id_membre + ": " +
            empruntService.isEmpruntPossible(membre));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}