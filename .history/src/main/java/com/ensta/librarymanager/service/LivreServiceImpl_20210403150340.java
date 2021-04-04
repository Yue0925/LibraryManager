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
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
}