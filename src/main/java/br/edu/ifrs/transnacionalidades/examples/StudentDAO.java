package br.edu.ifrs.transnacionalidades.examples;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public interface StudentDAO {

    public void create(Student student);

    public void update(Student student);

    public void delete(Long id);

    public void delete(Student student);

    public Student retrieve(Long id);

    public Student retrieve(String email);

    public List<Student> retrieve();
}