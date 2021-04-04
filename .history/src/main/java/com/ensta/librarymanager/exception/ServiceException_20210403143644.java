package com.ensta.librarymanager.exception;

/**
 * ServiceException
 */

public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;
    public ServiceException(){ super();}

    public ServiceException(String msg){ super(msg);}

    public ServiceException(String message, Throwable cause) {
		super(message, cause);
		cause.printStackTrace();
	}
}