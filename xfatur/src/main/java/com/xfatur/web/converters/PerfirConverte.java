package com.xfatur.web.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xfatur.model.security.Perfil;

@Component
public class PerfirConverte implements Converter<String[], List<Perfil>> {

    @Override
    public List<Perfil> convert(String[] source) {

	List<Perfil> perfis = new ArrayList<Perfil>();
	for (String id : source) {
	    if (!id.equals("0")) {
		perfis.add(new Perfil(Integer.parseInt(id)));
	    }
	}

	return perfis;
    }
}