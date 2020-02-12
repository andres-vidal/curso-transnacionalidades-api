import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PesquisadorDAO {

    @PersistenceContext
    private EntityManager em;

    public void criar (Pesquisador pesquisador) {

        em.persist(pesquisador);

    }

    public void atualizar (Pesquisador pesquisador) {

        em.persist(pesquisador);

    }

    public Pesquisador buscarPorId (Long id){

        return em.find(Pesquisador.class, id);

    }

    public Pesquisador buscarPorEmail (String email) {

        TypedQuery<Pesquisador> query = em.createQuery("SELECT p FROM Pesquisador p WHERE p.email = :email", Pesquisador.class);
        query.setParameter("email", email);

        try {

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

}