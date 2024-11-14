package com.Abhinandan.Ecommerce.Exceptions;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(){
        super("Unauthorized Access");
    }
}
