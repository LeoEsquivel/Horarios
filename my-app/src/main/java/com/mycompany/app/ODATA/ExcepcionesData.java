package com.mycompany.app.ODATA;

public class ExcepcionesData extends Exception {
    public ExcepcionesData(String message) {
        super(message);
    }

    public ExcepcionesData(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionesData(Throwable cause) {
        super(cause);
    }
}
