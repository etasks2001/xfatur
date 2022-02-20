package com.xfatur.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {

	@Override
	public Integer convert(String text) {

		String text2 = text.trim();

		if (text2.matches("[0-9]+")) {
			return Integer.valueOf(text2);
		}

		return null;
	}

}
