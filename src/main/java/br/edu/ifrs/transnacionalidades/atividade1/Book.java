package br.edu.ifrs.transnacionalidades.atividade1;

import java.time.Year;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Book{

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    @ManyToOne
    @NotEmpty
    private ArrayList<Author> author;

    @NotBlank
    @NotNull
    @Past
    @Convert(converter = Year.class)
    private Year publicationYear;

    @Column(unique = true)
    private int ISBN;

    public Book(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Author> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<Author> author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    
    
    

}