package br.com.codigomix.javer.error;

public class JaverException extends Exception {

    public JaverException() {
        super();
    }

    public JaverException(String message) {
        super(message);
    }

    public JaverException(String message, Throwable cause) {
        super(message, cause);
    }

    public JaverException(Throwable cause) {
        super(cause);
    }
}
