package get.sterlite.Exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInputsException.class)
    public ResponseEntity<Object> handleInvalidInputsException(
            InvalidInputsException ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, Object> body = getResponseBody(ex, request, httpStatus);

        return new ResponseEntity<>(body, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, Object> body = getResponseBody(ex, request, httpStatus);

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("message", errors);

        return new ResponseEntity<>(body, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object object,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, Object> body = getResponseBody(ex, request, httpStatus);

        httpStatus = updateExceptionMessage(ex, httpStatus, body);

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(
            Exception ex, WebRequest request) {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = getResponseBody(ex, request, httpStatus);

        httpStatus = updateExceptionMessage(ex, httpStatus, body);

        return new ResponseEntity<>(body, httpStatus);
    }

    private HttpStatus updateExceptionMessage(Exception ex, HttpStatus httpStatus, Map<String, Object> body) {
        if (ex instanceof InvalidInputsException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            updateResponseBody(body, ex, httpStatus);
            body.put("message", "Invalid Inputs");
        } else if (ex instanceof ConstraintViolationException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            updateResponseBody(body, ex, httpStatus);
            body.put("message", "Constraint Violation");
        } else if (ex instanceof UnexpectedRollbackException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            updateResponseBody(body, ex, httpStatus);
            body.put("message", "Unexpected Rollback");
        } else if (ex instanceof ConversionFailedException || ex instanceof NumberFormatException
                || ex instanceof IllegalArgumentException || ex instanceof HttpMessageNotReadableException
                || ex instanceof MethodArgumentTypeMismatchException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            updateResponseBody(body, ex, httpStatus);
            body.put("message", "Please Check the input values");
        } else if (ex instanceof UsernameNotFoundException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            updateResponseBody(body, ex, httpStatus);
        } else {
            body.put("message", "Something Went Wrong");
        }

        return httpStatus;
    }

    private Map<String, Object> getResponseBody(Exception ex, WebRequest request, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status code", httpStatus.value());
        body.put("status", httpStatus);
        body.put("reason", httpStatus.getReasonPhrase().toUpperCase());
        body.put("path", request.getDescription(false));
        body.put("message", ex.getMessage());

        return body;
    }

    private void updateResponseBody(Map<String, Object> body, Exception ex, HttpStatus httpStatus) {
        body.put("timestamp", LocalDateTime.now());
        body.put("status code", httpStatus.value());
        body.put("status", httpStatus);
        body.put("reason", httpStatus.getReasonPhrase().toUpperCase());
        body.put("message", ex.getMessage());
    }
}