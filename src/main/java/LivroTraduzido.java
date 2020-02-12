import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.edu.ifrs.transnacionalidades.examples.LocalDateConverter;

@Entity
@JsonInclude
public class LivroTraduzido extends Livro {

    @NotBlank
    @Convert(converter = LocalDateConverter.class)
    private LocalDate anoPublicacao;

    @NotBlank
    private String pais;

    private String isbn;

    public LivroTraduzido() {
    }

    public LivroTraduzido(@NotBlank String titulo, @NotBlank Autor autor, @NotBlank LocalDate anoPublicacao,
            @NotBlank String pais, String isbn) {
        super(titulo, autor);
        this.anoPublicacao = anoPublicacao;
        this.pais = pais;
        this.isbn = isbn;
    }

    public LocalDate getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(LocalDate anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}