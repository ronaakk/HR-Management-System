package com.jrp.pma.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jrp.pma.DAO.EmployeeRepository;
import com.jrp.pma.entities.Employee;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

	@Autowired
	EmployeeRepository empRepo;
	
	// this method will check whether the "value" (or email) is already in the database
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		Employee emp = empRepo.findByEmail(value);
		
		if (emp != null) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
