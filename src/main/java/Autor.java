import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@JsonInclude
public class Autor extends Pessoa {

    public Autor() {
    }

    public Autor(String nome, String sobrenome) {
        super(nome, sobrenome);
    }


    
}