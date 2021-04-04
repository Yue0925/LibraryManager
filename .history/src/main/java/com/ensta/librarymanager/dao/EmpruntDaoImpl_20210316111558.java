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

public class EmpruntDaoImpl implements EmpruntDao{

    /**
     * 
     */
    public List<Emprunt> getList() throws DaoException{

    }

	public List<Emprunt> getListCurrent() throws DaoException{

    }

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{

    }

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{

    }

	public Emprunt getById(int id) throws DaoException{

    }

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{

    }

	public void update(Emprunt emprunt) throws DaoException{

    }

	public int count() throws DaoException{

    }

}
