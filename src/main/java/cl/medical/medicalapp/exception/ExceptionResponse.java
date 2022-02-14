package cl.medical.medicalapp.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionResponse {

    private LocalDateTime timestamp;

    private Integer status;

    private String message;

    private List<String> error;

    private String type;

    private String path;

    public ExceptionResponse(LocalDateTime timestamp, Integer status, String message, List<String> error, String type, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.error = error;
        this.type = type;
        this.path = path;
    }

    public ExceptionResponse() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
