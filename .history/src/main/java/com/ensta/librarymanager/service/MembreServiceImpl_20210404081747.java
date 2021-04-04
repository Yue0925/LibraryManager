package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.exception.ServiceException;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

/**
 * MembreServiceImpl
 */
public class MembreServiceImpl implements MembreService {
    //Singleton
    private static MemberServiceImpl instance;
    private MemberServiceImpl(){};
    public static MemberServiceImpl getInstance(){
        if (instance == null) {instance = new MemberServiceImpl();}
        return instance;
    }
    
    @Override
    public List<Membre> getList() throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            return membreDao.getList();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getList() of MembreServiceImpl", e);
        }
    }

    /*
    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        
    }
    */

    @Override
    public Membre getById(int id) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            return membreDao.getById(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getById() of MembreServiceImpl", e);
        }
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone)
            throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        if (nom.length() == 0 || nom == null || prenom == null|| prenom.length() == 0) {
            throw new ServiceException("Nom or prenom is empty in create() of  MembreServiceImpl");
        }
        try {
            return membreDao.create(nom, prenom, adresse, email, telephone);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in create() of MembreServiceImpl", e);
        }
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        if (membre.getNom().length() == 0 || nom == null || prenom == null|| prenom.length() == 0) {
            throw new ServiceException("Nom or prenom is empty in create() of  MembreServiceImpl");
        }
    }


}