package com.Abhinandan.Ecommerce.Exceptions;

public class SessionExpiredException extends RuntimeException{
    public SessionExpiredException(){
        super("Session Expired!");
    }
}
