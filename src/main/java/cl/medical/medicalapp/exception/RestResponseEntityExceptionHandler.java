package cl.medical.medicalapp.exception;

import cl.medical.medicalapp.util.MessageSourceTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String FIELD_ERROR_SEPARATOR = ": ";

    private final MessageSourceTranslator messageSourceTranslator;

    @Autowired
    public RestResponseEntityExceptionHandler(MessageSourceTranslator messageSourceTranslator) {
        this.messageSourceTranslator = messageSourceTranslator;
    }

    @ExceptionHandler(Exception.class)
    protected final ResponseEntity<ExceptionResponse> handleAllUncaughtException(final Exception exception, final WebRequest request) {
        Optional<ResponseStatus> responseStatus = Optional.ofNullable(exception.getClass().getAnnotation(ResponseStatus.class));
        final HttpStatus status = responseStatus.isPresent() ? responseStatus.get().value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = exception.getLocalizedMessage();
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        ExceptionResponse exceptionResponse = this.getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler(NotFoundException.class)
    protected final ResponseEntity<ExceptionResponse> handleNotFound(final NotFoundException exception, final WebRequest request) {
        final String message = this.messageSourceTranslator.toLocale(exception.getMessage());
        final HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = this.getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ExceptionResponse> handleDataIntegrityViolation(final DataAccessException exception, WebRequest request) {
        final String message = exception.getMessage();
        final HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponse exceptionResponse = this.getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
        return new ResponseEntity<>(exceptionResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
                .collect(Collectors.toList());
        ExceptionResponse exceptionResponse = this.getExceptionResponseEntity(exception, status, request, validationErrors);
        return new ResponseEntity<>(exceptionResponse, status);
    }

    private ExceptionResponse getExceptionResponseEntity(final Exception exception, final HttpStatus status, final WebRequest request, final List<String> errors) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setError(errors);
        exceptionResponse.setType(exception.getClass().getSimpleName());
        exceptionResponse.setPath(request.getDescription(false));
        exceptionResponse.setMessage(status.getReasonPhrase());
        return exceptionResponse;
    }

}
