package br.edu.ifrs.transnacionalidades.examples;

public class StudentDoesNotExistException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StudentDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}