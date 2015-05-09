package com.example.vaadindemo.service;


import com.vaadin.data.Validator;


public class ISBNValidator implements Validator{
   public ISBNValidator() {

	}
    
    private static final long serialVersionUID = 1L;

	@Override
	public void validate(Object value) throws Validator.InvalidValueException {

		String valid = String.valueOf(value);
		valid.trim();
		
		if (!valid.substring(0,1).matches("^[1-9]$") || valid.isEmpty()) {
			throw new Validator.InvalidValueException("To nie jest liczba");
		}

	}
}
