import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Pessoa{

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;    

    public Pessoa() {}

    public Pessoa (String nome, String sobrenome) {

        this.nome = nome;
        this.sobrenome = sobrenome;

    }

    public String getNome() {

        return nome;

    }

    public void setNome(String nome) {

        this.nome = nome;

    }

    public String getSobrenome() {

        return sobrenome;

    }


    public void setSobrenome(String sobrenome) {

        this.sobrenome = sobrenome;

    }

}