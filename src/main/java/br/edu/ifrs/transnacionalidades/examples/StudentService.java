package br.edu.ifrs.transnacionalidades.examples;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class StudentService {

    @Inject
    private StudentDAO studentDAO;

    public void create(Student student) throws StudentExistsException {

        if (retrieve(student.getEmail()) == null) {

            studentDAO.create(student);

        } else {

            throw new StudentExistsException("A student with email " + student.getEmail() + " is already registered.");
        }
    }

    public void create(List<Student> students) throws StudentExistsException {

        for (Student student : students)
            create(student);
    }

    public Student retrieve(Long id) {

        return studentDAO.retrieve(id);
    }

    public Student retrieve(String email) {

        return studentDAO.retrieve(email);
    }

    public List<Student> retrieve() {

        return studentDAO.retrieve();
    }

    public void update(Student student) {

        studentDAO.update(student);
    }

    public void delete(Long id) {

        studentDAO.delete(id);
    }
}