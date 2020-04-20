package msg;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;

public class ResultMsg {
	String result;
	String message;	
	
	public ResultMsg(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}

	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResponseEntity<ResultMsg> response(String result, String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json; charset=utf-8");
		
		return new ResponseEntity<>(new ResultMsg(result, message), headers, HttpStatus.OK);
	}
}
