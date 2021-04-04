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
                    emprunts.add(new Emprunt(rs.getInt("id"), membreDao.getById(rs.getInt("idMembre")),
                    livreDao.getById(rs.getInt("idLivre")), rs.getDate("dateEmprunt").toLocalDate(), 
                    rs.getDate("dateRetour").toLocalDate()));
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
                    emprunts.add(new Emprunt(rs.getInt("id"), membreDao.getById(rs.getInt("idMembre")),
                    livreDao.getById(rs.getInt("idLivre")), rs.getDate("dateEmprunt").toLocalDate(), 
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
                    emprunts.add(new Emprunt(rs.getInt("id"), membreDao.getById(rs.getInt("idMembre")),
                    livreDao.getById(rs.getInt("idLivre")), rs.getDate("dateEmprunt").toLocalDate(), 
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
        String SELECT = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = " 
        + idLivre + ";";
        List<Emprunt> emprunts = new ArrayList<>();
        
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    Emprunt emprunt = new Emprunt();
                    emprunt.setId(rs.getInt("id"));

                    Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
                    emprunt.setMembre(membre);

                    Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
                    rs.getString("isbin"));
                    emprunt.setLivre(livre);
                    //TODO what if date is null?
                    emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
                    emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
                    emprunts.add(emprunt);
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
        String SELECT = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = " +
         id +";";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT)){
            ResultSet rs = preparedStatement.executeQuery();
            Emprunt emprunt = new Emprunt();
            emprunt.setId(rs.getInt("idEmprunt"));

            Membre membre = new Membre(rs.getInt("idMembre"), rs.getString("nom"), rs.getString("prenom"),
            rs.getString("adresse"), rs.getString("email"), rs.getString("telephone"), Abonnement.valueOf(rs.getString("abonnement")));
            emprunt.setMembre(membre);

            Livre livre = new Livre(rs.getInt("idLivre"), rs.getString("titre"), rs.getString("auteur"), 
            rs.getString("isbin"));
            emprunt.setLivre(livre);
            //TODO what if date is null?
            emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toLocalDate());
            emprunt.setDateRetour(rs.getDate("dateRetour").toLocalDate());
            return emprunt;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of EmpruntDaoImpl", e);
        }
    }

    /**
     * Créer un nouvel emprunt, return the inserted livre's id, or -1 if failed
     */
    @Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
        String REQUEST = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (" + idMembre +
        ", " + idLivre + ", " + dateEmprunt.toString() + ", " + null + ");";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.executeUpdate();
            //ResultSet rs = preparedStatement.getGeneratedKeys();
            return ; //rs.getInt(1);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in create() of EmpruntDaoImpl", e);
        }
    }

    /**
     * Mettre à jour un emprunt
     */
    @Override
	public void update(Emprunt emprunt) throws DaoException{
        String REQUEST = "UPDATE emprunt SET idMembre = " + emprunt.getMembre().getId() + ", idLivre = " +
        emprunt.getLivre().getId() + ", dateEmprunt = " + emprunt.getDateEmprunt().toString() + 
        ", dateRetour = " + emprunt.getDateRetour().toString() + " WHERE id = " + emprunt.getId() + ";" ;

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            preparedStatement.executeQuery();
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
        String REQUEST = "SELECT COUNT(id) AS count FROM emprunt;";

        try (Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(REQUEST)){
            ResultSet rs = preparedStatement.executeQuery();

            return rs.getInt("count");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new DaoException("Error in getById() of EmpruntDaoImpl", e);
        }
    }

}
