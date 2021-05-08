package myHomeworkBackend.core.utilities.results;

public class SuccessResult extends ResultBase{
	
	public SuccessResult(String message){
		this();
		setMessage(message);
	}
	
	public SuccessResult() {
		setSuccess(true);
	}
	
}
