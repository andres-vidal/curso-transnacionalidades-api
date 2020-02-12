package br.edu.ifrs.transnacionalidades.atividade1;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import br.edu.ifrs.transnacionalidades.examples.LocalDateConverter;

@Entity
public class User{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    @Size(min=8)
    private String password;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @Email
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;

    @PastOrPresent
    @Convert(converter = LocalDateConverter.class)
    private LocalDate lastLogin;

    public User(){

    }

    public User(String username, String password, String name, String surname, String email) {
        
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}

