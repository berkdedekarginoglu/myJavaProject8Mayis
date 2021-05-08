package myHomeworkBackend.consoleUI;

import myHomeworkBackend.business.concretes.AuthManager;
import myHomeworkBackend.business.concretes.IndividualCustomerManager;
import myHomeworkBackend.core.adapters.authenticationAdapters.Google.GoogleLoginServiceAdapter;
import myHomeworkBackend.core.adapters.emailAdapters.SomeEmailSenderAdapter;
import myHomeworkBackend.dataAccess.concretes.inMemory.InMemoryIndividualCustomerDao;
import myHomeworkBackend.entities.dtos.IndividualCustomerForLoginDto;
import myHomeworkBackend.entities.dtos.IndividualCustomerForRegisterDto;

public class Main {

	public static void main(String[] args) {
	
		IndividualCustomerForRegisterDto individualCustomerForRegisterDto = new IndividualCustomerForRegisterDto();
		individualCustomerForRegisterDto.setEmail("berk@berk.com");
		individualCustomerForRegisterDto.setFirstName("Berk");
		individualCustomerForRegisterDto.setLastName("Dedekargýnoðlu");
		individualCustomerForRegisterDto.setPassword("123456");
		
		IndividualCustomerManager individualCustomerManager = new IndividualCustomerManager(new InMemoryIndividualCustomerDao());
		
		AuthManager authManager = new AuthManager(individualCustomerManager,new SomeEmailSenderAdapter());
		
		var result = authManager.onBeforeRegistiration(individualCustomerForRegisterDto);
		
		if(!result.getSuccess())
			System.out.println(result.getMessage());
		else
		{
			var registirationResult = authManager.checkRegistirationCode("123456", individualCustomerForRegisterDto);
			System.out.println(registirationResult.getSuccess() + ":" + registirationResult.getMessage());
			
			IndividualCustomerForLoginDto individualCustomerForLoginDto = new IndividualCustomerForLoginDto();
			individualCustomerForLoginDto.setEmail("berk@berk.com");
			individualCustomerForLoginDto.setPassword("123456");
			
			var standartLoginResult = authManager.login(individualCustomerForLoginDto);
			System.out.println(standartLoginResult.getSuccess() + ":" + standartLoginResult.getMessage());
			
			var googleLogin = authManager.login(new GoogleLoginServiceAdapter());
			System.out.println(googleLogin.getSuccess() + ":" + googleLogin.getMessage());
		}
		
		
	}

}
