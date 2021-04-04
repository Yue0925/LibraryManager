package com.ensta.librarymanager.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Emprunts
 */
public class Emprunts {

    /**
     * Attributs
     */
    private int id;
    private Livre livre;
    private Membre membre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    
    /**
     * Constructor
     * @param id
     * @param livre
     * @param membre
     * @param dateEmprunt of format "yyyy-mm-dd"
     * @param dateRetour of format "yyyy-mm-dd"
     */
    Emprunts(int id, Livre livre, Membre membre, String dateEmprunt, String dateRetour){
        this.id = id;
        this.livre = livre;
        this.membre = membre;
        this.dateEmprunt =  LocalDate.parse(dateEmprunt);
        this.dateRetour = LocalDate.parse(dateRetour);
    }
    Emprunts(){}
}