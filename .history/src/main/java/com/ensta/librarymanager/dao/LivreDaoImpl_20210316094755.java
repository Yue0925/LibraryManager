package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.*;

public class LivreDaoImpl implements LivreDao{


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
            System.out.println(e);
        }catch (DaoException e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }


	public Livre getById(int id) throws DaoException{

    }
	public int create(String titre, String auteur, String isbn) throws DaoException{

    }
	public void update(Livre livre) throws DaoException{

    }
	public void delete(int id) throws DaoException{

    }
	public int count() throws DaoException{

    }
}

