import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@Stateless
public class PesquisadorServiceImpl implements PesquisadorService {

    @Inject
    public PesquisadorDAO pesquisadorDAO;

    private void validate(Pesquisador pesquisador) throws Exception {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Pesquisador>> violations = validator.validate(pesquisador
        );

        if (violations.size() > 0) {

            String message = "Falha na validação do Pesquisador. Violações são: " + violations.toString();
            throw new Exception();
        }
    }


    public void create(Pesquisador pesquisador) throws Exception {

        pesquisador.setId(null);
        validate(pesquisador);

        if (buscarPorEmail(pesquisador.getEmail()) != null) {

            String message = "Um pesquisador com o email " + pesquisador.getEmail() + " já foi cadastrada.";
            throw new Exception(message);
        }

        pesquisadorDAO.criar(pesquisador);
    }

}