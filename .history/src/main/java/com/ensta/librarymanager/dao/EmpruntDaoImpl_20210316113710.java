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
     * 
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

                       Membre membre = new Membre();
                       membre.setId(rs.getInt("idMembre"));
                       membre.setNom(rs.getString("nom"));
                       membre.setPrenom(rs.getString("prenom"));
                       membre.setAdresse(rs.getString("adresse"));
                       membre.setEmail(rs.getString("email"));
                       membre.setTelephone(rs.getString("telephone"));
                       membre.setAbonnement(Abonnement.valueOf(rs.getString("abonnement")));
                       
                       emprunts.add(emprunt);
                   }
                return emprunts;
        } catch (SQLException e){
            new DaoException("Error in getList() of LivreDaoImpl" + e.getMessage());
        }
        return emprunts;
    }

	public List<Emprunt> getListCurrent() throws DaoException{

    }

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{

    }

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{

    }

	public Emprunt getById(int id) throws DaoException{

    }

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{

    }

	public void update(Emprunt emprunt) throws DaoException{

    }

	public int count() throws DaoException{

    }

}
