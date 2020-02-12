package br.edu.ifrs.transnacionalidades.atividade1;

@Entity
public class Researcher extends User{

    private String institution;
    private String post;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    public Researcher(){

    }
    
    public Researcher(String username, String password, String name,String surname,
            String email, String institution, String post){
        super(username, password, name, surname, email);
        this.institution = institution;
        this.post = post;
    }
    

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    
   
}