package myHomeworkBackend.core.adapters.authenticationAdapters.Google;

import myHomeworkBackend.business.constants.Messages;
import myHomeworkBackend.core.adapters.authenticationAdapters.LoginService;
import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.core.utilities.results.SuccessResult;

public class GoogleLoginServiceAdapter implements LoginService{

	@Override
	public Result login() {
		GoogleLoginService googleLoginService = new GoogleLoginService();
		googleLoginService.login();
		return new SuccessResult(Messages.loggedInByGoogleSuccessfully);
	}

}
