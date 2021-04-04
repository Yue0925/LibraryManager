package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;

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
        
    }
}