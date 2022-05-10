package dashboard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "You didn't provide all data")
public class BadRequestException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}