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
    
}