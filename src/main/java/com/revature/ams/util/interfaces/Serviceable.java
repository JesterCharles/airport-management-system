package com.revature.ams.util.interfaces;


import com.revature.ams.util.exceptions.InvalidInputException;

public interface Serviceable<O> {
    O[] findAll();
    O create(O newObject) throws InvalidInputException;
    O findById(int number);
}
