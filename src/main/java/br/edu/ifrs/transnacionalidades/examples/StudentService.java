package br.edu.ifrs.transnacionalidades.examples;

import java.util.List;

public interface StudentService {

    public void create(Student student) throws StudentAlreadyExistsException, StudentValidationException;

    public void create(List<Student> students) throws StudentAlreadyExistsException, StudentValidationException;

    public Student retrieve(Long id);

    public Student retrieve(String email);

    public List<Student> retrieve();

    public void update(Student student)
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException;

    public void delete(Long id) throws StudentDoesNotExistException;
}