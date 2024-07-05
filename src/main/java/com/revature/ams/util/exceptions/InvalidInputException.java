package com.revature.ams.util.exceptions;

// OOP - Inheritence -
public class InvalidInputException extends Exception{
    public InvalidInputException(String message){
        super(message); // the constructor from the inherited class above
    }
}
