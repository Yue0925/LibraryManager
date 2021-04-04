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
//TODO close(); commit();

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
	public int create(String titre, String auteur, String isbn) throws DaoException{
        String REQUEST = "INSERT INTO livre(titre, auteur, isbn) VALUES (" + titre +", "+
        auteur +", "+ isbn +");";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            Livre livre = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"),
            rs.getString("isbn"));
            
        } catch (SQLException e){
            new DaoException("Error in create() of LivreDaoImpl" + e.getMessage());
        }
        
    }
	public void update(Livre livre) throws DaoException{

    }
	public void delete(int id) throws DaoException{

    }
	public int count() throws DaoException{

    }
}

