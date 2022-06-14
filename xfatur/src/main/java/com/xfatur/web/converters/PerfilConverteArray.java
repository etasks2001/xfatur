package com.xfatur.web.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xfatur.validation.dto.security.PerfilDTO;

@Component
public class PerfilConverteArray implements Converter<String[], List<PerfilDTO>> {

    @Override
    public List<PerfilDTO> convert(String[] source) {

	List<PerfilDTO> perfis = new ArrayList<PerfilDTO>();
	for (String id : source) {
	    if (!id.equals("0")) {
		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO.setId(Integer.parseInt(id));
		perfis.add(perfilDTO);
	    }
	}

	return perfis;
    }
}