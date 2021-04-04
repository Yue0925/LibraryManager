package com.ensta.librarymanager.service;

import java.util.List;
import java.util.ArrayList;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.model.Membre;

/**
 * MembreServiceImpl
 */
public class MembreServiceImpl implements MembreService {
    //Singleton
    private static MembreServiceImpl instance;
    private MembreServiceImpl(){};
    public static MembreServiceImpl getInstance(){
        if (instance == null) {instance = new MembreServiceImpl();}
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

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        MembreDao membreDao = MembreDaoImpl.getInstance();
        List<Membre> membres= new ArrayList<>();

        try {
            for(Membre membre : membreDao.getList()){
                if(empruntService.isEmpruntPossible(membre)){
                    membres.add(membre);}
            }
            return membres;
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());
            throw new ServiceException("Error in getListMembreEmpruntPossible() of MembreServiceImpl", e1);
        } catch (ServiceException e2) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getListMembreEmpruntPossible() of MembreServiceImpl", e2);
        }
    }

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
            String nomUpper = nom.toUpperCase();
            return membreDao.create(nomUpper, prenom, adresse, email, telephone);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in create() of MembreServiceImpl", e);
        }
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        if (membre.getNom().length() == 0 || membre.getNom() == null || 
        membre.getPrenom() == null|| membre.getPrenom().length() == 0) {
            throw new ServiceException("Nom or prenom is empty in update() of  MembreServiceImpl");
        }
        try {
            String nomUpper = membre.getNom().toUpperCase();
            membre.setNom(nomUpper);
            membreDao.update(membre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in update() of MembreServiceImpl", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            membreDao.delete(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in delete() of MembreServiceImpl", e);
        }
    }

    @Override
    public int count() throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            return membreDao.count();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in count() of MembreServiceImpl", e);
        }
    }

}