package com.ensta.librarymanager.model;

import java.time.LocalDate;

/**
 * Emprunt
 */
public class Emprunt {

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
    public Emprunt(int id, Livre livre, Membre membre, LocalDate dateEmprunt, LocalDate dateRetour){
        this.id = id;
        this.livre = livre;
        this.membre = membre;
        this.dateEmprunt =  dateEmprunt;
        this.dateRetour = dateRetour;
    }
    public Emprunt(){}

    /**
     * Overload toString
     */
    @Override
    public String toString() {
        return "id: "+id+" livre: " +livre+" membre: "+membre + " dateEmprunt: "+
        dateEmprunt+" dateRetour: "+dateRetour+"";
    }

    /**
     * Getters
     */
    public int getId() {
        return id;
    }
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    public LocalDate getDateRetour() {
        return dateRetour;
    }
    public Livre getLivre() {
        return livre;
    }
    public Membre getMembre() {
        return membre;
    }

    /**
     * Setters
     */
    public void setId(int id) {
        this.id = id;
    }
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }
    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    public void setMembre(Membre membre) {
        this.membre = membre;
    }

}