package com.ensta.librarymanager.model;

import com.ensta.librarymanager.utils.*;

/**
 * Membre
 */
public class Membre {

    /**
     * Attributs
     */
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;

    /**
     * Cnnstructor
     * @param id
     * @param nom
     * @param prenom
     * @param adresse
     * @param email
     * @param telephone
     * @param abonnement
     */
    Membre(int id, String nom, String prenom, String adresse, String email,
    String telephone, Abonnement abonnement){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.abonnement = abonnement;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
    }
    Membre(){}

    @Override
    public String toString() {
        return "id: "+id + " nom: " + nom + " prenom: "+prenom+
        " adresse: " + adresse + " email: "+email+" telephone: "+telephone+
        " abonnement: "+abonnement;
    }

    /**
     * Getters
     */
    public Abonnement getAbonnement() {
        return abonnement;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setters
     */
    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}