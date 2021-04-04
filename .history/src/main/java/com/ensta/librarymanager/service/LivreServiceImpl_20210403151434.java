package com.ensta.librarymanager.service;
import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * LivreServiceImpl
 */
public class LivreServiceImpl implements LivreService{
    //Singleton
    private static LivreServiceImpl instance;
    private LivreServiceImpl(){};
    public static LivreServiceImpl getInstance(){
        if (instance == null) instance = new LivreServiceImpl();
        return instance;
    }

    @Override
    public List<Livre> getList() throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        List<Livre> livres = new ArrayList<>();

        try {
            livres = livreDao.getList();
            return livres;
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getList() of LivreServiceImpl", e);
        }
    }

    /*
    @Override
    public List<Livre> getListDispo() throws ServiceException {
        
    }
    */

    @Override
    public Livre getById(int id) throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            Livre livre = livreDao.getById(id);
            return livre;
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in getById() of LivreServiceImpl", e);
        }
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException {
        if(titre.length() == 0){ throw new ServiceException("Error in create() of LivreServiceImpl");}
        LivreDao livreDao = LivreDaoImpl.getInstance();

        try {
            int id = livreDao.create(titre, auteur, isbn);
            return id;
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in create() of LivreServiceImpl", e);
        }
    }

    @Override
    public void update(Livre livre) throws ServiceException {}{
        if(livre.getTitre().length() == 0){ throw new ServiceException("Error in update() of LivreServiceImpl");}
        LivreDao livreDao = LivreDaoImpl.getInstance();

        try {
            livreDao.update(livre);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in update() of LivreServiceImpl", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            livreDao.delete(id);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Error in delete() of LivreServiceImpl", e);
        }
    }
    
}