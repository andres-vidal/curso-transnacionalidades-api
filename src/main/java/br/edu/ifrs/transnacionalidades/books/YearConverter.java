package br.edu.ifrs.transnacionalidades.books;

import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(Year attribute) {
        return (short) attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Short data) {
        return Year.of(data);
    }
}
