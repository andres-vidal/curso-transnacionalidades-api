package br.edu.ifrs.transnacionalidades.examples;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class StudentDAO {

    @PersistenceContext
    private EntityManager em;

    public void create(Student student) {

        em.persist(student);
    }

    public void update(Student student) {

        em.merge(student);
    }

    public void delete(Long id) {

        Student student = em.getReference(Student.class, id);
        em.remove(student);
    }

    public Student retrieve(Long id) {

        return em.find(Student.class, id);
    }

    public Student retrieve(String email) {

        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);

        try {

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

    public List<Student> retrieve() {

        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }
}