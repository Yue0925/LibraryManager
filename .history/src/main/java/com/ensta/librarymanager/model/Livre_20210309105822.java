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

    @Override
    public String toString() {
        return "id: "+id +" isbn: "+isbn+" titre: "+titre+" auteur: "+auteur;
    }

    /**
     * Getters
     */
    public String getTitre() {
        return titre;
    }
    public int getId() {
        return id;
    }
    public String getAuteur() {
        return auteur;
    }
    public String getIsbn() {
        return isbn;
    }

    /**
     * Setters
     */
    public void setId(int id) {
        this.id = id;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
}