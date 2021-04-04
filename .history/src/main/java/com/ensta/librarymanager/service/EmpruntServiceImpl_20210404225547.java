package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Emprunt;
import com.ensta.librarymanager.model.Membre;

/**
 * EmpruntServiceImpl
 */
public class EmpruntServiceImpl implements EmpruntService {
    //Singleton
    private static EmpruntServiceImpl instance;
    private EmpruntServiceImpl(){};
    
    /** 
     * @return EmpruntServiceImpl
     */
    public static EmpruntServiceImpl getInstance(){
        if (instance == null) {instance = new EmpruntServiceImpl();}
        return instance;
    }
 
    
    /** 
     * @return List<Emprunt>
     * @throws ServiceException
     */
    @Override
    public List<Emprunt> getList() throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getList() of EmpruntServiceImpl", e);
        }
    }

    
    /** 
     * @return List<Emprunt>
     * @throws ServiceException
     */
    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunt = new ArrayList<>();
        try {
            emprunt = empruntDao.getListCurrent();
            return emprunt;
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getListCurrent() of EmpruntServiceImpl", e);
        } 
    }

    
    /** 
     * @param idMembre
     * @return List<Emprunt>
     * @throws ServiceException
     */
    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getListCurrentByMembre() of EmpruntServiceImpl", e);
        }  
    }

    
    /** 
     * @param idLivre
     * @return List<Emprunt>
     * @throws ServiceException
     */
    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getListCurrentByLivre() of EmpruntServiceImpl", e);
        }     
    }

    
    /** 
     * @param id
     * @return Emprunt
     * @throws ServiceException
     */
    @Override
    public Emprunt getById(int id) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getById() of EmpruntServiceImpl", e);
        }
    }

    
    /** 
     * @param idMembre
     * @param idLivre
     * @param dateEmprunt
     * @throws ServiceException
     */
    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            empruntDao.create(idMembre, idLivre, dateEmprunt);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in create() of EmpruntServiceImpl", e);
        }
    }

    
    
    /** 
     * @param id
     * @throws ServiceException
     */
    @Override
    public void returnBook(int id) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            Emprunt emprunt = empruntDao.getById(id);
            emprunt.setDateRetour(LocalDate.now());
            empruntDao.update(emprunt);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in returnBook() of EmpruntServiceImpl", e);
        }
    }

    
    /** 
     * @return int
     * @throws ServiceException
     */
    @Override
    public int count() throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in count() of EmpruntServiceImpl", e);
        }
    }

    
    /** 
     * @param idLivre
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.getListCurrentByLivre(idLivre).isEmpty();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in isLivreDispo() of EmpruntServiceImpl", e);
        }
    }

    
    /** 
     * @param membre
     * @return boolean
     * @throws ServiceException
     */
    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            return empruntDao.getListCurrentByMembre(membre.getId()).size() < 
            membre.getAbonnement().getNbEmprunts();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in isEmpruntPossible() of EmpruntServiceImpl", e);        }
    }

}