package br.edu.ifrs.transnacionalidades.examples;

public class StudentExistsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public StudentExistsException(String errorMessage) {
        super(errorMessage);
    }
}