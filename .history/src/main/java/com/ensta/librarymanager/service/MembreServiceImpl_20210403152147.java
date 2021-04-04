package com.ensta.librarymanager.service;

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
    
    
}