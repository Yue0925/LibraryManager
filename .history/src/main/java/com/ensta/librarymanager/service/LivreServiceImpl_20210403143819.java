package com.ensta.librarymanager.service;
import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.model.*;

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
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("Problème lors de la recherche des livres", e);
        }
        return livres;
    }
    
}