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

public class MembreDaoImpl implements MembreDao {

    /**
     * Return a list of members in database
     */
    public List<Membre> getList() throws DaoException{
        String SELECT = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
        List<Membre> membres = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                   ResultSet rs = preparedStatement.executeQuery();
                   while (rs.next()){
                       Membre membre = new Membre();
                       membre.setId(rs.getInt("id"));
                       membre.setNom(rs.getString("nom"));
                       membre.setPrenom(rs.getString("prenom"));
                       membre.setAdresse(rs.getString("adresse"));
                       membre.setEmail(rs.getString("email"));
                       membre.setTelephone(rs.getString("telephone"));
                       membre.setAbonnement( Abonnement.valueOf(rs.getString("abonnement") ));
                       membres.add(membre);
                   }
                return membres;
        } catch (SQLException e){
            new DaoException("Error in getList() of MembreDaoImpl" + e.getMessage());
        }
        return membres;
    }

	public Membre getById(int id) throws DaoException{
        String SELECT = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = " + id +";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
            ResultSet rs = preparedStatement.executeQuery();
            Livre livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
            rs.getString("isbn"));
            return livre;
        } catch (SQLException e){
            new DaoException("Error in getById() of LivreDaoImpl" + e.getMessage());
        }
        return new Livre();
    }

	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{

    }

	public void update(Membre membre) throws DaoException{

    }

	public void delete(int id) throws DaoException{

    }

	public int count() throws DaoException{

    }
}
