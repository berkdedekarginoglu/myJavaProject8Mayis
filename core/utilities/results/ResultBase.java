package myHomeworkBackend.core.utilities.results;

public class ResultBase implements Result {

	private String message;
	private Boolean success;
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public Boolean getSuccess() {
		return success;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
