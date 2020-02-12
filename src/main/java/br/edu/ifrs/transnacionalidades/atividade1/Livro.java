package br.edu.ifrs.transnacionalidades.atividade1;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Livro {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String titulo, pais;

    @Past
    @NotNull
    private LocalDate anoDePublicacao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(LocalDate anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}