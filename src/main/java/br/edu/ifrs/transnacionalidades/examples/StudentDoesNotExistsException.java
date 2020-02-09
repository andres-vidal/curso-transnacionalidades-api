package br.edu.ifrs.transnacionalidades.examples;

public class StudentDoesNotExistsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StudentDoesNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}