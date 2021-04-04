package com.ensta.librarymanager.service;

import java.util.List;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.model.*;

import java.util.ArrayList;
import java.util.List;

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
        List<Membre> membres = new ArrayList<>();

        
    }
}