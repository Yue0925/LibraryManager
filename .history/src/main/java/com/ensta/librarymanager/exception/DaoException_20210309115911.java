package com.ensta.librarymanager.exception;

/**
 * DaoException
 */
public class DaoException extends Exception {
    private static final long serialVersionUID = 1L;
    public BoardException(){ super();}

    public BoardException(String msg){ super(msg);}
    
}