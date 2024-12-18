package ru.kpfu.exceptions;

public class PersonalFormAlreadyExistsException extends Exception{
    public PersonalFormAlreadyExistsException(String message){
        super(message);
    }
}
