package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;

/**
 * EmpruntServiceImpl
 */
public class EmpruntServiceImpl implements EmpruntService {
    //Singleton
    private static EmpruntServiceImpl instance;
    private EmpruntServiceImpl(){};
    public static EmpruntServiceImpl getInstance(){
        if (instance == null) {instance = new EmpruntServiceImpl();}
        return instance;
    }
 
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

    /*
    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        
    }
    */

    /*
    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        
    }
    */

    /*
    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        
    }
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
}