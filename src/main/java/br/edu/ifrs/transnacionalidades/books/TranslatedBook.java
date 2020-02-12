package br.edu.ifrs.transnacionalidades.books;

import java.time.Year;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.ISBN;

@Entity
public class TranslatedBook extends Book {

    @Past
    @NotNull
    @Convert(converter = YearConverter.class)
    private Year year;

    @NotNull
    @NotBlank
    private String country;

    @ISBN
    private String publisher;

    @ManyToOne
    private OriginalBook original;

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public OriginalBook getOriginal() {
        return original;
    }

    public void setOriginal(OriginalBook original) {
        this.original = original;
    }
}