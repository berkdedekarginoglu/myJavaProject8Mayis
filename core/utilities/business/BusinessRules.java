package myHomeworkBackend.core.utilities.business;


import myHomeworkBackend.core.utilities.results.ErrorResult;
import myHomeworkBackend.core.utilities.results.Result;
import myHomeworkBackend.core.utilities.results.SuccessResult;

public class BusinessRules {
	public static Result RunRules(Result... rules) {
		for(Result rule : rules)
			if(!rule.getSuccess())
				return new ErrorResult(rule.getMessage());
		return new SuccessResult();
	}
}
