package com.gustavo.mag_bank.services.exceptions;

public class ClientAlreadyRegistered extends RuntimeException{
    public ClientAlreadyRegistered(String message){
        super(message);
    }
}
