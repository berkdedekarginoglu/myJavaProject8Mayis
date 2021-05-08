package myHomeworkBackend.core.utilities.results;

public class ErrorResult extends ResultBase{

	public ErrorResult(String message){
		this();
		setMessage(message);
	}
	
	public ErrorResult() {
		setSuccess(false);
	}
}
