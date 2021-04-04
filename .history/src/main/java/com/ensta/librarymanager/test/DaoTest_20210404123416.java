package com.ensta.librarymanager.test;

import com.ensta.librarymanager.utils.*;

import com.ensta.librarymanager.model.*;

import com.ensta.librarymanager.dao.*;
import java.time.LocalDate;

public class DaoTest {
	public static void main(String[] args) throws Exception {		
		try {
			//FillDatabase.main(args);
			MembreDao membreDao= MembreDaoImpl.getInstance();
			LivreDao livreDao = LivreDaoImpl.getInstance();
			EmpruntDao empruntDao= EmpruntDaoImpl.getInstance();
			// Teste member
			int id_membre= membreDao.create("nom1", "prenom2", "adresse1", "nom1.prenom2@ensta-paris.fr",
			 "06000000");
			Membre membre=membreDao.getById(id_membre);
			membre.setAdresse("complète adresse1");
			membre.setAbonnement(Abonnement.VIP);
			membreDao.update(membre);
			
			// Teste livre
			int id_livre = livreDao.create("the great gatsby", "F. Scott Fitzgerald", "IBSN000");
			Livre livre = livreDao.getById(id_livre);
			livre.setTitre("The great Gatsby");
			livre.setIsbn("IBSN001");
			livreDao.update(livre);

			// Teste emprunt 
			LocalDate date_retour= LocalDate.of(2021,01,01);
			empruntDao.create(id_membre, id_livre, date_retour);
			empruntDao.getList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
