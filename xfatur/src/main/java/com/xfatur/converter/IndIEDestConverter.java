package com.xfatur.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.xfatur.model.IndIEDest;

@Converter(autoApply = true)
public class IndIEDestConverter implements AttributeConverter<IndIEDest, String> {

    @Override
    public String convertToDatabaseColumn(IndIEDest indIEDest) {
	if (indIEDest == null) {
	    return null;
	}
	return indIEDest.getCodigo();
    }

    @Override
    public IndIEDest convertToEntityAttribute(String codigo) {
	if (codigo == null) {
	    return null;
	}

	return Stream.of(IndIEDest.values()).filter(c -> c.getCodigo().equals(codigo)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}