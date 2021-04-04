package com.ensta.librarymanager.model;

import java.sql.Date;

/**
 * Emprunts
 */
public class Emprunts {

    private int id;
    private Livre livre;
    private Membre membre;
    private Date dateEmprunt;
    private Date dateRetour;
    
}