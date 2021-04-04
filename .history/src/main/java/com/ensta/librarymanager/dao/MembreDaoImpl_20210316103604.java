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

public class MembreDaoImpl implements MembreDao {
    /**
     * 
     */
    public List<Membre> getList() throws DaoException{
        String SELECT = "SELECT id, nom, prenom, adresse, email, telephone, abonnement 
        FROM membre
        ORDER BY nom, prenom;";
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

	public Membre getById(int id) throws DaoException{

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
