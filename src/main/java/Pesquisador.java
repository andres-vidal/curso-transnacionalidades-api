import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude
public class Pesquisador extends Pessoa {

    @NotBlank
    @Column(unique = true)
    private String email;

    private String nacionalidade;
    private String afiliacao;
    private String ocupacao;

    public Pesquisador() {}

    public Pesquisador(String nome, String sobrenome, String email,
                       String nacionalidade, String afiliacao, String ocupacao) {
        
        super(nome, sobrenome);
        this.email = email;
        this.nacionalidade = nacionalidade;
        this.afiliacao = afiliacao;
        this.ocupacao = ocupacao;

    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacionaliade() {
        return nacionalidade;
    }

    public void setNacionaliade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    

}