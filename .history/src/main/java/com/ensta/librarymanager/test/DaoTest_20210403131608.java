package com.ensta.librarymanager.test;

import com.ensta.librarymanager.utils.*;

import com.ensta.librarymanager.model.*;

import com.ensta.librarymanager.dao.*;
import java.time.LocalDate;

public class DaoTest {
    public static void main(String[] args) {
		
        MembreDao membreImpl= MembreDaoImpl.getInstance();
		LivreDao livreImpl = LivreDaoImpl.getInstance();
        EmpruntDao empruntImpl= EmpruntDaoImpl.getInstance();
        
        try {
			// Teste member
			int id_membre= membreImpl.create("nom1", "prenom2", "adresse1", "nom1.prenom2@ensta-paris.fr",
			 "06000000");
			Membre membre=membreImpl.getById(id_membre);
			membre.setAdresse("complète adresse1");
			membre.setAbonnement(Abonnement.VIP);
			membreImpl.update(membre);
			
			// Teste livre
			int id_livre = livreImpl.create("the great gatsby", "F. Scott Fitzgerald", "IBSN000");
			Livre livre = livreImpl.getById(id_livre);
			livre.setTitre("The great Gatsby");
			livre.setIsbn("IBSN001");
			livreImpl.update(livre);

			// Teste emprunt 
			LocalDate date_retour= LocalDate.of(2021,01,01);
			int id_emprunt = empruntImpl.create(id_membre, id_livre, date_retour);
			Emprunt emprunt=empruntImpl.getById(id_emprunt);

			LocalDate date_emprunt_update= LocalDate.of(2021,02,01);
			emprunt.setDateEmprunt(date_emprunt_update);
			empruntImpl.update(emprunt);   
			
        } catch (Exception e) {
            System.out.println(e.getMessage());
		}

    }
}
