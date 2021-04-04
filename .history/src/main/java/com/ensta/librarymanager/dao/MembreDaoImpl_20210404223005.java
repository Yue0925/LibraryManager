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
    
    private static final String CREATE_QUERY = "INSERT INTO membre (nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ONE_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";
    private static final String SELECT_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
    private static final String UPDATE_QUERY = "UPDATE membre SET nom=?, prenom=?, adresse=?, email=?, telephone=?, abonnement=? WHERE id=?;";
    private static final String DELETE_QUERY = "DELETE FROM Membre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM Membre;";
    
    /**
     * Return a list of members in database
     */
    @Override
    public List<Membre> getList() throws DaoException{
        List<Membre> membres = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY);
               ResultSet rs = preparedStatement.executeQuery();){
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

    private ResultSet GetByIdStatement(int id, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }
    
    /**
     * Return the member found by the given id in database
     */
    @Override
	public Membre getById(int id) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ONE_QUERY);
        ResultSet rs = GetByIdStatement(id, preparedStatement)){
            if(rs.next()) { 
                return new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), 
            rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), 
            Abonnement.valueOf(rs.getString("abonnement") ));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of MembreDaoImpl", e);
        }
        return new Membre();
    }

    private ResultSet CreateStatement(String nom, String prenom, String adresse, String email, String telephone, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);
        preparedStatement.setString(3, adresse);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, telephone);
        preparedStatement.setString(6, Abonnement.BASIC.getName());

        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    /**
     * Return the inserted member's id
     */
    @Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = CreateStatement(nom, prenom, adresse, email, telephone, preparedStatement)){
            if(rs.next()) { return rs.getInt(1);}
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in create() of MembreDaoImpl", e);
        }
        return -1;
    }

    private void UpdateStatement(PreparedStatement preparedStatement, Membre membre) throws DaoException{
        try {
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getEmail());
            preparedStatement.setString(4, membre.getAdresse());
            preparedStatement.setString(5, membre.getTelephone());
            preparedStatement.setString(6, membre.getAbonnement().toString());
            preparedStatement.setInt(7, membre.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DaoException("Error in UpdateStatement() of MembreDaoImpl", e);
        }

    }
    /**
     * Given a membre, update its informations in database
     */
    @Override
	public void update(Membre membre) throws DaoException{
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            UpdateStatement(preparedStatement, membre);
            preparedStatement.close();
            conn.close();
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
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
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
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(COUNT_QUERY);
        ResultSet rs = preparedStatement.executeQuery()){
            if(rs.next()) { return rs.getInt(1);}
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of LivreDaoImpl", e);
        }
        return -1;
    }

}
