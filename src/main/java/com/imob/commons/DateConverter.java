package com.imob.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class DateConverter implements Converter<String, Date> {
	@Override 
	public Date convert(String source) {				
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(source);
		} catch (ParseException e) {
			try {
				return new SimpleDateFormat("yyyy/MM/dd").parse(source);
			} catch (ParseException e1) {				
			}			
		}
		return null;
	}
}
