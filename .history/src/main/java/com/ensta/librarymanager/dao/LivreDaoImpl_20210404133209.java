package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.*;

public class LivreDaoImpl implements LivreDao{

    // Singleton
    private static LivreDaoImpl instance;
    private LivreDaoImpl(){};
    public static LivreDaoImpl getInstance(){
        if (instance == null) {instance = new LivreDaoImpl();}
        return instance;
    }

    private static final String CREATE_QUERY = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)";
    private static final String SELECT_ONE_QUERY = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT id, titre, auteur, isbn FROM livre";
    private static final String UPDATE_QUERY = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM livre WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM livre";

    /**
     * Return all livre in the database
     */
    @Override
    public List<Livre> getList() throws DaoException{
        List<Livre> livres = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY)){
                   ResultSet rs = preparedStatement.executeQuery();
                   while (rs.next()){
                       Livre livre =  new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
                       rs.getString("isbn"));
                       livres.add(livre);
                   }
                return livres;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getList() of LivreDaoImpl", e);
        }
    }

    private ResultSet GetByIdStatement(PreparedStatement preparedStatement, int id) throws SQLException {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    /**
     * Return the livre found by given id in the database
     */
    @Override
	public Livre getById(int id) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ONE_QUERY);
        ResultSet rs = GetByIdStatement(preparedStatement, id)
        ){
            if(rs.next()) { 
                return new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
            rs.getString("isbn"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of LivreDaoImpl", e);
        }
        return new Livre();
}

    private ResultSet CreateStatement(PreparedStatement preparedStatement, String titre, String auteur, String isbn ) throws SQLException {
        preparedStatement.setString(1, titre);
        preparedStatement.setString(2, auteur);
        preparedStatement.setString(3, isbn);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    /**
     * Return the inserted livre's id, or -1 if failed
     */
    @Override
	public int create(String titre, String auteur, String isbn) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = CreateStatement(preparedStatement, titre, auteur, isbn)){
            if(rs.next()){ return rs.getInt(1);}
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in create() of LivreDaoImpl", e);
        }
        return -1;
    }

    /**
     * Given a livre, update its informations in database
     */
    @Override
	public void update(Livre livre) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)){
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in update() of LivreDaoImpl", e);
        }
    }

    /**
     * Given an id, delete the livre in database 
     */
    @Override
	public void delete(int id) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in delete() of LivreDaoImpl", e);
        }
    }

    /**
     * Return the number of livres in database, or -1 if failed
     */
    @Override
	public int count() throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(COUNT_QUERY);
        ResultSet rs = preparedStatement.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of LivreDaoImpl", e);
        }
        return -1;
    }
}

