package util.interfaces;


import util.exceptions.InvalidInputException;

public interface Serviceable<O> {
    O[] findAll();
    void create(O newObject) throws InvalidInputException;
    O findById(int number);
}
