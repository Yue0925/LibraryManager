package com.ensta.librarymanager.service;

import java.util.List;

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
    public com.ensta.librarymanager.service.Membre getById(int id) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}