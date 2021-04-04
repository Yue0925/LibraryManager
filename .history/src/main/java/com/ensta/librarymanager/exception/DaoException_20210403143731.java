package com.ensta.librarymanager.exception;

/**
 * DaoException
 */
public class DaoException extends Exception {
    private static final long serialVersionUID = 1L;
    public DaoException(){ super();}

    public DaoException(String msg){ super(msg);}
    
    public DaoException(String message, Throwable cause) {
		super(message, cause);
		cause.printStackTrace();
	}
}