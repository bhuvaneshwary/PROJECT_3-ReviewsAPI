package com.udacity.course3.reviews.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * The exception thrown when an non-existent id is used for a query
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The requested Id does not exist!")
public class IdNotFoundException extends RuntimeException {

}
