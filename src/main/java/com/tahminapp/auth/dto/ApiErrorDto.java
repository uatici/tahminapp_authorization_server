package com.tahminapp.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDto {

    @JsonIgnore
    private HttpStatus httpStatus;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private Object subErrors;

    private ApiErrorDto() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrorDto(HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
        this.status = generateStatusFromHttpStatus();
    }

    public ApiErrorDto(HttpStatus httpStatus, Throwable ex) {
        this();
        this.httpStatus = httpStatus;
        this.status = generateStatusFromHttpStatus();
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorDto(HttpStatus httpStatus, String message, Throwable ex) {
        this();
        this.httpStatus = httpStatus;
        this.status = generateStatusFromHttpStatus();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorDto(HttpStatus httpStatus, String message, Object subErrors, Throwable ex) {
        this();
        this.httpStatus = httpStatus;
        this.status = generateStatusFromHttpStatus();
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
        this.subErrors = subErrors;
    }

    private String generateStatusFromHttpStatus () {
        if(httpStatus == null )
            return null;
        return httpStatus.value() + " " + httpStatus.getReasonPhrase();
    }
}