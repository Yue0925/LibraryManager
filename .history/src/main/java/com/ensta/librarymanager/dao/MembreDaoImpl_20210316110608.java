package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.utils.Abonnement;

import java.lang.reflect.Member;
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

    /**
     * Return the member found by the given id in database
     */
	public Membre getById(int id) throws DaoException{
        String SELECT = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = " + id +";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
            ResultSet rs = preparedStatement.executeQuery();

            Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), 
            rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), 
            Abonnement.valueOf(rs.getString("abonnement") ));
            return membre;
        } catch (SQLException e){
            new DaoException("Error in getById() of MembreDaoImpl" + e.getMessage());
        }
        return new Membre();
    }

    /**
     * Return the inserted member's id, or -1 if failed
     */
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{
        String REQUEST = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (" + 
        nom + ", " + prenom + ", " + adresse + ", " + email + ", " + telephone + ", " + Abonnement.BASIC.name() + ");";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            return rs.getInt(1);
        } catch (SQLException e){
            new DaoException("Error in create() of MembreDaoImpl" + e.getMessage());
        }
        return -1;
    }

    /**
     * 
     */
	public void update(Membre membre) throws DaoException{

    }

	public void delete(int id) throws DaoException{

    }

	public int count() throws DaoException{

    }
}
