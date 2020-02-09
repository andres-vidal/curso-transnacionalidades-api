package br.edu.ifrs.transnacionalidades.examples;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            return Date.valueOf(attribute);
        } else {
            return null;
        }
    }

    public LocalDate convertToEntityAttribute(Date dbData) {
        if (dbData != null) {
            return dbData.toLocalDate();
        } else {
            return null;
        }
    }
}