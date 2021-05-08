package myHomeworkBackend.business.concretes;



import myHomeworkBackend.business.abstracts.AuthService;
import myHomeworkBackend.business.abstracts.IndividualCustomerService;
import myHomeworkBackend.business.constants.Messages;
import myHomeworkBackend.core.adapters.authenticationAdapters.LoginService;
import myHomeworkBackend.core.adapters.emailAdapters.EmailSenderService;
import myHomeworkBackend.core.utilities.business.BusinessRules;
import myHomeworkBackend.core.utilities.results.ErrorResult;
import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.core.utilities.results.SuccessResult;
import myHomeworkBackend.core.utilities.security.hashing.HashingHelper;
import myHomeworkBackend.entities.dtos.IndividualCustomerForLoginDto;
import myHomeworkBackend.entities.dtos.IndividualCustomerForRegisterDto;

public class AuthManager implements AuthService {

	private IndividualCustomerService individualCustomerService;
	private EmailSenderService emailSenderService;
	public AuthManager(IndividualCustomerService individualCustomerService,EmailSenderService emailSenderService) {
		this.individualCustomerService = individualCustomerService;
		this.emailSenderService = emailSenderService;
	}
	
	@Override
	public Result onBeforeRegistiration(IndividualCustomerForRegisterDto individualCustomerForRegisterDto) {
		
		var authRulesResult = BusinessRules.RunRules(
				checkIsUserExist(individualCustomerForRegisterDto.getEmail())) ;
		
		if(!authRulesResult.getSuccess())
			return new ErrorResult(authRulesResult.getMessage()); // Eðer kullanýcý mevcut ise
		
		var registirationRules = individualCustomerService.checkRulesOnRegistiration(individualCustomerForRegisterDto);
		
		if(!registirationRules.getSuccess())
			return new ErrorResult(registirationRules.getMessage());
		
		var verifactionMailResult = 
				emailSenderService.SendEmail(individualCustomerForRegisterDto.getEmail(),"foo@boo.com" ,"Doðrulama kodunuz 123456");
		
		if(!verifactionMailResult.getSuccess())
			return new ErrorResult(verifactionMailResult.getMessage()); // Eðer doðrulama epostasý gönderilemez ise
		
		return new SuccessResult(Messages.registirationEmailSent);
	}
	
	@Override
	public Result register(IndividualCustomerForRegisterDto individualCustomerForRegisterDto) {
		
		var customerAddedResult = individualCustomerService.add(individualCustomerForRegisterDto);
		
		if(!customerAddedResult.getSuccess())
			return new ErrorResult(customerAddedResult.getMessage());
		
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	@Override
	public Result login(IndividualCustomerForLoginDto individualCustomerForLoginDto) {
		var selectedUser = checkIsUserExist(individualCustomerForLoginDto.getEmail());
		if(!selectedUser.getSuccess())
			return new ErrorResult(Messages.userNotFound);
		
		var isPasswordCorrect = 
				
				HashingHelper.verifyPassword(
						individualCustomerForLoginDto.getPassword(),
						individualCustomerService.getByEmail(individualCustomerForLoginDto.getEmail()).getPasswordHash());
		
		if(isPasswordCorrect)
			return new SuccessResult(Messages.loggedInSuccessfully);
		return new ErrorResult(Messages.userNotFound);
	}
	
	@Override
	public Result login(LoginService loginService) {
		return loginService.login();
	}
	
	
	@Override
	public Result checkIsUserExist(String email) {
		if(email=="test@test.com")
			return new ErrorResult(Messages.emailExist);
		return new SuccessResult();
	}
	
	@Override
	public Result checkRegistirationCode(String registirationCode,IndividualCustomerForRegisterDto individualCustomerForRegisterDto) {
		
		if(registirationCode=="123456")
		return register(individualCustomerForRegisterDto);
		
		return new ErrorResult(Messages.registirationCodeIsNotValid);
		
	}

}
