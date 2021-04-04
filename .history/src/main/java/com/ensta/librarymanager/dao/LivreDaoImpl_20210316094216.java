package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.*;

public class LivreDaoImpl implements LivreDao{


    public List<Livre> getList() throws DaoException{
        String SELECT = "SELECT id, titre, auteur, isbn FROM livre;";
        List<Livre> livres = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
               PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL)){
                   ResultSet rs = preparedStatement.executeQuery();
                   while (rs.next()){
                       Film film =  new Film();
                       film.setId(rs.getInt("id"));
                       film.setRealisateur(rs.getString("realisateur"));
                       film.setTitre(rs.getString("titre"));
                       result.add(film);
                   }
                return result;
        } catch (SQLException e){
            System.out.println(e);
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

