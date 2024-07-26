package com.revature.ams.util.interfaces;


import com.revature.ams.util.exceptions.InvalidInputException;

import java.util.List;

public interface Serviceable<O> extends Crudable<O> {
    List<O> findAll();
    O create(O newObject);
    O findById(int number);
}
