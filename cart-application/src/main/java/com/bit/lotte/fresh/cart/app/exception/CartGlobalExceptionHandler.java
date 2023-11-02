package com.bit.lotte.fresh.cart.app.exception;

import com.bit.lotte.fresh.cart.common.application.handler.ErrorDTO;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CartGlobalExceptionHandler {
  @ExceptionHandler(CartDomainException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorDTO handleCartDomainException(
      CartDomainException cartDomainException) {
    String exceptionMessage = cartDomainException.getMessage();
    return ErrorDTO.builder()
        .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .status(HttpStatus.valueOf(400))
        .message(exceptionMessage)
        .build();
  }
}
