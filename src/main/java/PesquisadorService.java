import java.util.List;

public interface PesquisadorService {

    public void criar(Pesquisador pesquisador);

    public void criar(List<Pesquisador> pesquisadores);

    public void atualizar (Pesquisador pesquisador);

    public Pesquisador buscarPorId (Long id);

    public Pesquisador buscarPorEmail(String email);

}