package myHomeworkBackend.business.abstracts;

import java.util.List;

import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.entities.concretes.IndividualCustomer;
import myHomeworkBackend.entities.dtos.IndividualCustomerForRegisterDto;

public interface IndividualCustomerService {

	Result add(IndividualCustomerForRegisterDto individualCustomerForRegisterDto);
	
	IndividualCustomer getByEmail(String email);
	
	List<IndividualCustomer> getAllByActivationStatus(Boolean status);
	
	Result checkRulesOnRegistiration(IndividualCustomerForRegisterDto individualCustomerForRegisterDto);
	
}
