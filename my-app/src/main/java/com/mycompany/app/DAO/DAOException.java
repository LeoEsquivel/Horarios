package com.mycompany.app.DAO;

public class DAOException extends Exception {
    public DAOException(String s) {
    }
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    
}
