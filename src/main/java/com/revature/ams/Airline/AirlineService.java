package com.revature.ams.Airline;

import com.revature.ams.util.interfaces.Serviceable;

import java.util.List;

public class AirlineService implements Serviceable<Airline> {
    @Override
    public List<Airline> findAll() {
        return List.of();
    }

    @Override
    public Airline create(Airline newObject) {
        return null;
    }

    @Override
    public Airline findById(int number) {
        return null;
    }

    public Airline filterByAirline(){
        return null;
    }

    @Override
    public boolean update(Airline updatedObject) {
        return false;
    }

    @Override
    public boolean delete(Airline removedObject) {
        return false;
    }
}
