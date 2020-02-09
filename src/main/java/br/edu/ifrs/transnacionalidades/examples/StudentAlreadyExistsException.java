package br.edu.ifrs.transnacionalidades.examples;

public class StudentAlreadyExistsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StudentAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}