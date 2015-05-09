package com.example.vaadindemo.service;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.DoubleValidator;


public class DoubleRangeValidator extends DoubleValidator implements Validator {

	private static final long serialVersionUID = 1L;

	private double min = 1.00;
	private double max = 100.00;
	private String errorMessage = "Błędna wartość";

	public DoubleRangeValidator(double min, double max, String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.min = min;
		this.max = max;
	}

	@Override
	public void validate(Object value) throws InvalidValueException {
		super.validate(value);
		if (!isValid(value))
			throw new InvalidValueException(errorMessage);
	}

	@Override
	public boolean isValid(Object value) {
		if (!super.isValid(value))
			return false;

		Double v = new Double((String) value);
		if (v.doubleValue() < min || v.doubleValue() > max) {
			return false;
		}
		return true;
	}

	
}
