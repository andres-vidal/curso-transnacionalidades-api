package br.edu.ifrs.transnacionalidades.books;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class OriginalBook extends Book {

    @OneToMany(mappedBy = "original")
    private List<TranslatedBook> translations;

    public List<TranslatedBook> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslatedBook> translations) {
        this.translations = translations;
    }
}