package br.edu.ifrs.transnacionalidades.atividade1;

import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;


@Entity
public class TranslatedBook extends Book{

    @NotEmpty
    private Book originalBook;

    @ElementCollection
    @NotEmpty
    private ArrayList<String> country;
    
    
}