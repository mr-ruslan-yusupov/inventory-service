package com.inventory.service.exceptions.handler;

import com.inventory.service.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class InventoryExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(InventoryExceptionHandler.class);

    @ExceptionHandler(BrandNotFoundException.class)
    public @ResponseBody ExceptionResponse handleBrandNotFoundException(BrandNotFoundException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.NOT_FOUND, request, ex);
    }

    @ExceptionHandler(BrandNotEmptyException.class)
    public @ResponseBody ExceptionResponse handleBrandNotEmptyException(BrandNotEmptyException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY, request, ex);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public @ResponseBody ExceptionResponse handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.NOT_FOUND, request, ex);
    }

    @ExceptionHandler(CategoryNotEmptyException.class)
    public @ResponseBody ExceptionResponse handleCategoryNotEmptyException(CategoryNotEmptyException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY, request, ex);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public @ResponseBody ExceptionResponse handleStoreNotFoundException(StoreNotFoundException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.NOT_FOUND, request, ex);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public @ResponseBody ExceptionResponse handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.NOT_FOUND, request, ex);
    }

//    @ExceptionHandler(UnprocessableEntityException.class)
//    public @ResponseBody ExceptionResponse handleUnprocessableEntityException(UnprocessableEntityException ex, WebRequest request) {
//        return createExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY, request, ex);
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public @ResponseBody ExceptionResponse handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
//        return createExceptionResponse(HttpStatus.UNAUTHORIZED, request, ex);
//    }

    @ExceptionHandler(ServerException.class)
    public @ResponseBody ExceptionResponse handleServerException(ServerException ex, WebRequest request) {
        return createExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, ex);
    }

    private ExceptionResponse createExceptionResponse(HttpStatus httpStatus, WebRequest request, Exception ex) {
        final String message = ex.getMessage();
        final String path = request.getDescription(false);

        LOG.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new ExceptionResponse(httpStatus, path, message);
    }
}