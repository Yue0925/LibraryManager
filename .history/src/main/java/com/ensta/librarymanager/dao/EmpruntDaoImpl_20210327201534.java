package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.utils.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.*;

public class EmpruntDaoImpl implements EmpruntDao{

    /**
     * Return all emprunts records in the database
     */
    public List<Emprunt> getList() throws DaoException{
        String SELECT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Emprunt emprunt = new Emprunt();
                    emprunt.setId(rs.getInt("id"));

                    Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
                    emprunt.setMembre(membre);

                    Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
                    rs.getString("isbin"));
                    emprunt.setLivre(livre);

                    emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                    emprunts.add(emprunt);
                }
            return emprunts;
        } catch (SQLException e){
            new DaoException("Error in getList() of EmpruntDaoImpl" + e.getMessage());
        }
        return emprunts;
    }

    /**
     * Lister les emprunts pas encore rendus
     */
	public List<Emprunt> getListCurrent() throws DaoException{
        String SELECT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Emprunt emprunt = new Emprunt();
                    emprunt.setId(rs.getInt("id"));

                    Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
                    emprunt.setMembre(membre);

                    Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
                    rs.getString("isbin"));
                    emprunt.setLivre(livre);
                    //TODO what if date is null?
                    emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                    emprunts.add(emprunt);
                }
            return emprunts;
        } catch (SQLException e){
            new DaoException("Error in getListCurrent() of EmpruntDaoImpl" + e.getMessage());
        }
        return emprunts;

    }

    /**
     * Lister les emprunts pas encore rendus pour un membre donné
     */
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
        String SELECT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = " +
        idMembre + ";";
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Emprunt emprunt = new Emprunt();
                    emprunt.setId(rs.getInt("id"));

                    Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
                    emprunt.setMembre(membre);

                    Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
                    rs.getString("isbin"));
                    emprunt.setLivre(livre);
                    //TODO what if date is null?
                    emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                    emprunts.add(emprunt);
                }
            return emprunts;
        } catch (SQLException e){
            new DaoException("Error in getListCurrentByMembre() of EmpruntDaoImpl" + e.getMessage());
        }
        return emprunts;
    }

    /**
     * Lister les emprunts pas encore rendus pour un livre donné
     */
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
        String SELECT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = " 
        + idLivre + ";";
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Emprunt emprunt = new Emprunt();
                    emprunt.setId(rs.getInt("id"));

                    Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
                    emprunt.setMembre(membre);

                    Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
                    rs.getString("isbin"));
                    emprunt.setLivre(livre);
                    //TODO what if date is null?
                    emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                    emprunts.add(emprunt);
                }
            return emprunts;
        } catch (SQLException e){
            new DaoException("Error in getListCurrentByLivre() of EmpruntDaoImpl" + e.getMessage());
        }
        return emprunts;

    }

    /**
     * Récupérer un emprunt par son identifiant
     */
    @Override
	public Emprunt getById(int id) throws DaoException{
        String SELECT = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = " +
         id +";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
            ResultSet rs = preparedStatement.executeQuery();
            Emprunt emprunt = new Emprunt();
            emprunt.setId(rs.getInt("idEmprunt"));

            Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
            rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
            emprunt.setMembre(membre);

            Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
            rs.getString("isbin"));
            emprunt.setLivre(livre);
            //TODO what if date is null?
            emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
            emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
            return emprunt;
        } catch (SQLException e){
            new DaoException("Error in getById() of LivreDaoImpl" + e.getMessage());
        }
        return new Emprunt();
    }

    /**
     * Créer un nouvel emprunt
     */
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{

    }

	public void update(Emprunt emprunt) throws DaoException{

    }

	public int count() throws DaoException{

    }

}
