package com.ensta.librarymanager.dao;
import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.persistence.ConnectionManager;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.utils.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.*;

public class EmpruntDaoImpl implements EmpruntDao{

    // Singleton
    private static EmpruntDaoImpl instance;
    private EmpruntDaoImpl(){};
    public static EmpruntDaoImpl getInstance(){
        if (instance == null) {instance = new EmpruntDaoImpl();}
        return instance;
    }

    private static final String CREATE_QUERY = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt) VALUES (?, ?, ?)";
    private static final String SELECT_ONE_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC";
    private static final String UPDATE_QUERY = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?";
    private static final String SELECT_CURRENT_BY_MEMBRE = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?";
    private static final String SELECT_CURRENT_BY_LIVRE = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt";
    private static final String SELECT_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL";


    /**
     * Return all emprunts records in the database
     */
    @Override
    public List<Emprunt> getList() throws DaoException{
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY);
            ResultSet rs = preparedStatement.executeQuery()){
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao livreDao = LivreDaoImpl.getInstance();

                while (rs.next()){
                    emprunts.add(new Emprunt(rs.getInt("id"), livreDao.getById(rs.getInt("idLivre")), 
                    membreDao.getById(rs.getInt("idMembre")), rs.getDate("dateEmprunt").toLocalDate().toString(), 
                    rs.getDate("dateRetour").toLocalDate().toString()));
                }
            return emprunts;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getList() of EmpruntDaoImpl", e);
        }
    }

    /**
     * Lister les emprunts pas encore rendus
     */
    @Override
	public List<Emprunt> getListCurrent() throws DaoException{
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENT_QUERY);
            ResultSet rs = preparedStatement.executeQuery()){
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao livreDao = LivreDaoImpl.getInstance();

                while (rs.next()){
                    emprunts.add(new Emprunt(rs.getInt("id"), livreDao.getById(rs.getInt("idLivre")), 
                    membreDao.getById(rs.getInt("idMembre")), rs.getDate("dateEmprunt").toLocalDate(), 
                    rs.getDate("dateRetour").toLocalDate()));
                }
            return emprunts;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getListCurrent() of EmpruntDaoImpl", e);
        }
    }

    private ResultSet GetByIdStatement(int id, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    /**
     * Lister les emprunts pas encore rendus pour un membre donné
     */
    @Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENT_BY_MEMBRE);
            ResultSet rs = GetByIdStatement(idMembre, preparedStatement)){
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao livreDao = LivreDaoImpl.getInstance();

                while (rs.next()){
                    emprunts.add(new Emprunt(rs.getInt("id"), livreDao.getById(rs.getInt("idLivre")), 
                    membreDao.getById(rs.getInt("idMembre")), rs.getDate("dateEmprunt").toLocalDate(), 
                    rs.getDate("dateRetour").toLocalDate()));
                }
            return emprunts;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getListCurrentByMembre() of EmpruntDaoImpl", e);
        }
    }

    /**
     * Lister les emprunts pas encore rendus pour un livre donné
     */
    @Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENT_BY_LIVRE);
            ResultSet rs = GetByIdStatement(idLivre, preparedStatement)){
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao livreDao = LivreDaoImpl.getInstance();

                while (rs.next()){
                    emprunts.add(new Emprunt(rs.getInt("id"), livreDao.getById(rs.getInt("idLivre")), 
                    membreDao.getById(rs.getInt("idMembre")), rs.getDate("dateEmprunt").toLocalDate(), 
                    rs.getDate("dateRetour").toLocalDate()));
                }
            return emprunts;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getListCurrentByLivre() of EmpruntDaoImpl", e);
        }
    }

    /**
     * Récupérer un emprunt par son identifiant
     */
    @Override
	public Emprunt getById(int id) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ONE_QUERY);
        ResultSet rs = GetByIdStatement(id, preparedStatement)){
            MembreDao membreDao = MembreDaoImpl.getInstance();
            LivreDao livreDao = LivreDaoImpl.getInstance();

            if(rs.next()) {
                return new Emprunt(rs.getInt("id"), livreDao.getById(rs.getInt("idLivre")), 
                membreDao.getById(rs.getInt("idMembre")), rs.getDate("dateEmprunt").toLocalDate(), 
                rs.getDate("dateRetour").toLocalDate());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of EmpruntDaoImpl", e);
        }
        return new Emprunt();
    }

    private ResultSet CreateStatement(int idMembre, int idLivre, LocalDate dateEmprunt, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, idMembre);
        preparedStatement.setInt(2, idLivre);
        preparedStatement.setString(3, dateEmprunt.toString());
        preparedStatement.setDate(4, null);

        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    /**
     * Créer un nouvel emprunt, return the inserted livre's id, or -1 if failed
     */
    @Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = CreateStatement(idMembre, idLivre, dateEmprunt, preparedStatement)){
            return ; //rs.getInt(1);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in create() of EmpruntDaoImpl", e);
        }
    }

    private void UpdateStatement(PreparedStatement preparedStatement, Emprunt emprunt){
        preparedStatement.setInt(1, emprunt.getMembre().getId());
        preparedStatement.setInt(2, emprunt.getLivre().getId());
        preparedStatement.setString(3, emprunt.getDateEmprunt().toString());
        preparedStatement.setString(4, emprunt.getDateRetour().toString());
        preparedStatement.setInt(5, emprunt.getId());
        preparedStatement.executeUpdate();
    }

    /**
     * Mettre à jour un emprunt
     */
    @Override
	public void update(Emprunt emprunt) throws DaoException{
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            UpdateStatement(preparedStatement, emprunt);
            preparedStatement.executeQuery();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in update() of EmpruntDaoImpl", e);
        }
    }

    /**
     * Compter le nombre d’emprunts total
     */
    @Override
	public int count() throws DaoException{
        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(COUNT_QUERY);
        ResultSet rs = preparedStatement.executeQuery()){
            if(rs.next()) { return rs.getInt(1);}
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of EmpruntDaoImpl", e);
        }
        return -1;
    }

}
