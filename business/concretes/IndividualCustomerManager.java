package myHomeworkBackend.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myHomeworkBackend.business.abstracts.IndividualCustomerService;
import myHomeworkBackend.business.constants.Messages;
import myHomeworkBackend.core.utilities.business.BusinessRules;
import myHomeworkBackend.core.utilities.results.ErrorResult;
import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.core.utilities.results.SuccessResult;
import myHomeworkBackend.core.utilities.security.hashing.HashingHelper;
import myHomeworkBackend.dataAccess.abstracts.IndividualCustomerDao;
import myHomeworkBackend.entities.concretes.IndividualCustomer;
import myHomeworkBackend.entities.dtos.IndividualCustomerForRegisterDto;

public class IndividualCustomerManager implements IndividualCustomerService{

	private IndividualCustomerDao individualCustomerDao;
	
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao) {
		this.individualCustomerDao = individualCustomerDao;
	}
	
	
	@Override
	public Result add(IndividualCustomerForRegisterDto individualCustomerForRegisterDto) {
		
		IndividualCustomer individualCustomer = new IndividualCustomer();
		
		var generatedPassword = HashingHelper.createPasswordHash(individualCustomerForRegisterDto.getPassword());
		
		individualCustomer.setPasswordHash(generatedPassword);
		individualCustomer.setCreatedAt(new Date(System.currentTimeMillis()));
		individualCustomer.setModifiedAt(new Date(System.currentTimeMillis()));
		individualCustomer.setEmail(individualCustomerForRegisterDto.getEmail());
		individualCustomer.setFirstName(individualCustomerForRegisterDto.getFirstName());
		individualCustomer.setLastName(individualCustomerForRegisterDto.getLastName());
		individualCustomer.setIsActive(true);
		
		this.individualCustomerDao.Add(individualCustomer);
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	@Override
	public List<IndividualCustomer> getAllByActivationStatus(Boolean status) {
		
		return individualCustomerDao.GetAll(i -> status.equals(i.getIsActive()));
	}
	
	@Override
	public IndividualCustomer getByEmail(String email) {
		return individualCustomerDao.Get(i -> email.equals(i.getEmail()));
	}
	
	public  Result isFirstNameValid(String firstName) {

		if(firstName.length() < 2)
			return new ErrorResult(Messages.firstNameMustBeGreatherThenTwoChars);
		return new SuccessResult();
	}
	
	public  Result isLastNameValid(String lastName) {
		if(lastName.length() < 2)
			return new ErrorResult(Messages.lastameMustBeGreatherThenTwoChars);
		return new SuccessResult();
	}
	
	public  Result isPasswordValid(String password) {
		if(password.length() < 6)
			return new ErrorResult(Messages.passwordLengthMustBeGreatherThenSixDigit);
		return new SuccessResult();
	}
	
	public  Result isEmailValid(String email) {
		
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		
		if(!matcher.matches())
			return new ErrorResult(Messages.emailNotValid);
		return new SuccessResult();
	}
	
	


	@Override
	

	public Result checkRulesOnRegistiration(IndividualCustomerForRegisterDto individualCustomerForRegisterDto) {
		
		var checkRules = BusinessRules.RunRules(
				isFirstNameValid(individualCustomerForRegisterDto.getFirstName()),
				isLastNameValid(individualCustomerForRegisterDto.getLastName()),
				isPasswordValid(individualCustomerForRegisterDto.getPassword()),
				isEmailValid(individualCustomerForRegisterDto.getEmail())
				);
				
		if(!checkRules.getSuccess())
			return new ErrorResult(checkRules.getMessage());
		
		return new SuccessResult();
		
	}

}
