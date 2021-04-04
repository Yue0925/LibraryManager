package com.ensta.librarymanager.utils;

/**
 * Abonnement
 */
public enum Abonnement {
    BASIC("Basic", 2),
    PREMIUM("Premium", 5),
    VIP("Vip", 20);

    /**
     * Attributs
     */
    private String name;
    private int nbEmprunts;

    Abonnement(String name, int nbEmprunts){
        this.name = name;
        this.nbEmprunts = nbEmprunts;
    }

    /**
     * Overload toString()
     */
    @Override
    public String toString() {
        return name+" accès emprunts: "+nbEmprunts;
    }
    
}