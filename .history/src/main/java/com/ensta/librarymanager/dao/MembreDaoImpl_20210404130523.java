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

    // Singleton
    private static MembreDaoImpl instance;
    private MembreDaoImpl(){};
    public static MembreDaoImpl getInstance(){
        if (instance == null) {instance = new MembreDaoImpl();}
        return instance;
    }
    
    private static final String CREATE_QUERY = "INSERT INTO Membre (nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM Membre WHERE id=?;";
    private static final String SELECT_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
    private static final String UPDATE_QUERY = "UPDATE Membre SET nom=?, prenom=?, adresse=?, email=?, telephone=?, abonnement=? WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM Membre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM Membre;";
    
    /**
     * Return a list of members in database
     */
    @Override
    public List<Membre> getList() throws DaoException{
        List<Membre> membres = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                   ResultSet rs = preparedStatement.executeQuery();
                   while (rs.next()){
                       Membre membre = new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), 
                       rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement") ));
                       membres.add(membre);
                   }
                return membres;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getList() of MembreDaoImpl", e);
        }
    }

    /**
     * Return the member found by the given id in database
     */
    @Override
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
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of MembreDaoImpl", e);
        }
    }

    /**
     * Return the inserted member's id
     */
    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{
        String REQUEST = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (" + 
        nom + ", " + prenom + ", " + adresse + ", " + email + ", " + telephone + ", " + Abonnement.BASIC.name() + ");";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            return rs.getInt(1);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in create() of MembreDaoImpl", e);
        }
    }

    /**
     * Given a membre, update its informations in database
     */
    @Override
	public void update(Membre membre) throws DaoException{
        String REQUEST = "UPDATE membre SET nom = " + membre.getNom()+ ", prenom = " + membre.getPrenom()+", adresse = " +
        membre.getAdresse() + ", email = " + membre.getEmail() + ", telephone = " + membre.getTelephone() + 
        ", abonnement = " + membre.getAbonnement().name() + "WHERE id = " + membre.getId() + ";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            preparedStatement.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in update() of MembreDaoImpl", e);
        }
    }

    /**
     * Given an id, delete the member in database 
     */
    @Override
	public void delete(int id) throws DaoException{
        String REQUEST = "DELETE FROM membre WHERE id = " + id + ";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            preparedStatement.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in delete() of MembreDaoImpl", e);
        }
    }

    /**
     * Return the number of members in database, or -1 if failed
     */
    @Override
	public int count() throws DaoException{
        String REQUEST = "SELECT COUNT(id) AS count FROM membre;";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            ResultSet rs = preparedStatement.executeQuery();

            return rs.getInt("count");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of LivreDaoImpl", e);
        }
    }

}
