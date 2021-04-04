package com.ensta.librarymanager.model;

/**
 * Livre
 */
public class Livre {

    /**
     * Attributs
     */
    private int id;
    private String titre;
    private String auteur;
    private String isbn;

    /**
     * Constructor
     * @param id
     * @param titre
     * @param auteur
     * @param isbn
     */
    Livre(int id, String titre, String auteur, String isbn){
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
    }
    Livre(){}
}