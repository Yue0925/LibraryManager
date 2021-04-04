package com.ensta.librarymanager.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Emprunts
 */
public class Emprunts {

    private int id;
    private Livre livre;
    private Membre membre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    
}