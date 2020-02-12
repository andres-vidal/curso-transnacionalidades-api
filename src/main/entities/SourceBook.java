package br.edu.ifrs.transnacionalidades.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Id;
import javax.validation.Past;


@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @Past
    @Convert(converter = YearConverter.class)
    private LocalDate date;

    @Max(20)
    @NotNull
    @NotBlank
    private String language;

    @Max(20)
    private String country;

    private String isbn;
    

    public String getId() {
        return id;
    }
    public void set(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}