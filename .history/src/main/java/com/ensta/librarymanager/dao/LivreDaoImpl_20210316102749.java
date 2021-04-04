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

    /**
     * Return all livre in the database
     */
    public List<Livre> getList() throws DaoException{
        String SELECT = "SELECT id, titre, auteur, isbn FROM livre;";
        List<Livre> livres = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                   ResultSet rs = preparedStatement.executeQuery();
                   while (rs.next()){
                       Livre livre =  new Livre();
                       livre.setId(rs.getInt("id"));
                       livre.setTitre(rs.getString("titre"));
                       livre.setAuteur(rs.getString("auteur"));
                       livre.setIsbn(rs.getString("isbn"));
                       livres.add(livre);
                   }
                return livres;
        } catch (SQLException e){
            new DaoException("Error in getList() of LivreDaoImpl" + e.getMessage());
        }
        return livres;
    }

    /**
     * Return the livre found by given id in the database
     */
	public Livre getById(int id) throws DaoException{
        String SELECT = "SELECT id, titre, auteur, isbn FROM livre WHERE id =" + id +";";

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

    /**
     * Return the inserted livre's id, or -1 if failed
     */
	public int create(String titre, String auteur, String isbn) throws DaoException{
        String REQUEST = "INSERT INTO livre(titre, auteur, isbn) VALUES (" + titre +", "+
        auteur +", "+ isbn +");";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            return rs.getInt(1);
        } catch (SQLException e){
            new DaoException("Error in create() of LivreDaoImpl" + e.getMessage());
        }
        return -1;
    }

    /**
     * Given a livre, update its informations in database
     */
	public void update(Livre livre) throws DaoException{
        String REQUEST = "UPDATE livre SET titre = " + livre.getTitre() + ", auteur = " + livre.getAuteur() +
        ", isbn = " + livre.getIsbn() + " WHERE id = " + livre.getId() + ";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            preparedStatement.executeQuery();
        } catch (SQLException e){
            new DaoException("Error in update() of LivreDaoImpl" + e.getMessage());
        }
    }

    /**
     * Given an id, delete the livre in database 
     */
	public void delete(int id) throws DaoException{
        String REQUEST = "DELETE FROM livre WHERE id = " + id + ";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            preparedStatement.executeQuery();
        } catch (SQLException e){
            new DaoException("Error in delete() of LivreDaoImpl" + e.getMessage());
        }
    }

    /**
     * 
     */
	public int count() throws DaoException{

    }
}

