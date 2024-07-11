package com.revature.ams.util.exceptions;

// OOP - Inheritence -
public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message){
        super(message); // the constructor from the inherited class above
    }
}
