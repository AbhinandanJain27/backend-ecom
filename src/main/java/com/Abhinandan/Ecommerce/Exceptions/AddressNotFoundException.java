package com.Abhinandan.Ecommerce.Exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(){
        super("Address Not Found! Kindly add Address");
    }
}
