package ticketmachine.model.authentication;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class RequestStatus {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;


    public RequestStatus(HttpStatus httpStatus, String message, String path) {
        this.message = message;
        this.path = path;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
