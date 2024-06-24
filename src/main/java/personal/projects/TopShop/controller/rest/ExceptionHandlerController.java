package personal.projects.TopShop.controller.rest;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import personal.projects.TopShop.domaine.exception.ErrorResponse;
import personal.projects.TopShop.service.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception){

        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());

        return new ResponseEntity<>(
                new ErrorResponse("Functional errors",details),
                HttpStatus.BAD_REQUEST
        );
    }


//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<Object> handleExpiredJwt(ExpiredJwtException exception){
//
//        System.out.println("inside Expired jwt exception handler");
//        List<String> details = new ArrayList<>();
//        details.add(exception.getLocalizedMessage());
//
//        return new ResponseEntity<>(
//                new ErrorResponse("Session timeout",details),
//                HttpStatus.LOCKED
//        );
//    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Technical error, please consult your administrator", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
