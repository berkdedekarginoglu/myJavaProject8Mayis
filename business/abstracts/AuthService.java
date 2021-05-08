package myHomeworkBackend.business.abstracts;

import myHomeworkBackend.core.adapters.authenticationAdapters.LoginService;
import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.entities.dtos.IndividualCustomerForLoginDto;
import myHomeworkBackend.entities.dtos.IndividualCustomerForRegisterDto;

public interface AuthService {

	Result register(IndividualCustomerForRegisterDto individualCustomerForRegisterDto);
	
	Result login(IndividualCustomerForLoginDto individualCustomerForLoginDto);
	
	Result login(LoginService loginService);
	
	Result checkIsUserExist(String email);
	
	Result checkRegistirationCode(String registirationCode,IndividualCustomerForRegisterDto individualCustomerForRegisterDto);
	
	Result onBeforeRegistiration(IndividualCustomerForRegisterDto individualCustomerForRegisterDto);
}
