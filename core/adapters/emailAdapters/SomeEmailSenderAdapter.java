package myHomeworkBackend.core.adapters.emailAdapters;

import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.core.utilities.results.SuccessResult;

public class SomeEmailSenderAdapter implements EmailSenderService{

	@Override
	public Result SendEmail(String sendTo, String from, String content) {
		SomeEmailSender someEmailSender = new SomeEmailSender();
		someEmailSender.Send(sendTo,from,content);
		return new SuccessResult();
	}

}
