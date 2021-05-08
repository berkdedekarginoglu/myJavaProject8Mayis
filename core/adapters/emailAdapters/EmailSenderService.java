package myHomeworkBackend.core.adapters.emailAdapters;

import myHomeworkBackend.core.utilities.results.Result;

public interface EmailSenderService {

	Result SendEmail(String sendTo,String from,String content);
}
