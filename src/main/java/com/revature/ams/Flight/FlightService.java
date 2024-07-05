package com.revature.ams.Flight;

import com.revature.ams.util.exceptions.InvalidInputException;
import com.revature.ams.util.interfaces.Serviceable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

// TODO: Implement the Service Layer, this contains all Business Logic (anything that makes sense in the scope of a business)
public class FlightService implements Serviceable<Flight> {
    private Flight[] flights = new Flight[5];
    // -> lamba: format () -> {}, defining any parameteres used by the function and it's execution. Parenthesis not necessary for oen parameter
    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();

    @Override
    public Flight[] findAll(){
        if (isEmpty(flights)) {
            System.out.println("No flight info");
            return null;
        } else {
            System.out.println("Returning flight information....");
            return flights;
        }
    }

    @Override
    public Flight create(Flight flight) throws InvalidInputException {
        validateMinFlight(flight);
        int indexToReplace = getFirstNull(flights);
        if (indexToReplace == -1) {
            System.out.println("Sorry, our flight database is full, please try again later");
        }  else {
            flights[indexToReplace] = flight;
            System.out.printf("Flight %s successfully added\n", flight);
        }
        flight.setFlightNumber(1);
        return flight;
    }

    @Override
    public Flight findById(int flightNumber){
        for (Flight flight : flights) {
            if (flight != null && flight.getFlightNumber()== flightNumber) {
                return flight;
            }
        }

        return null;
    }

    public boolean update(Flight flightToUpdate, String timeArrival, String timeDeparture, int pilot, int airline) throws InvalidInputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        flightToUpdate.setTimeArrival(LocalDateTime.parse(timeArrival, formatter));
        flightToUpdate.setTimeDeparture(LocalDateTime.parse(timeDeparture, formatter));
        flightToUpdate.setPilot(pilot);
        flightToUpdate.setAirline(airline);

        validateFullFlight(flightToUpdate);

        return true;
    }


    public boolean isEmpty(Flight[] arr) { // defining the parameter of a string array to be included when executing this mehtod
        for (Flight element : arr) { // enhanced for each loop, that iterates through the arrays elements returning & assigning the value to the declared variable flight
            if (element != null) {
                return false;
            }
        }
        return true;
    }

    public int getFirstNull(Flight[] arr) { //get the first null index to replace, otherwise return -1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return i;
            }
        }
        return -1;
    }

    // ducking the exception
    public void validateMinFlight(Flight flight) throws InvalidInputException {
        if (flight == null) {
            throw new InvalidInputException("Flight is null as it has not been instantiated in memory");
        }

        // potentially bad errors with using public attributes, both reassigning the value & reading with the same expression
        // we've reassigned the value
        //flight.flightNumber = 55555;
        //flight.setFlightNumber(55555);
        // Now assigned a new variable by 'getting' the flight number using dot (.) syntax
        //int flightTempNumber = flight.flightNumber;
        int flightTempNumber = flight.getFlightNumber();

        // alt + shift : multiple cursors
        if (flight.getFlightNumber() < 1000 || flight.getFlightNumber() > 999999) {
            throw new InvalidInputException("Flight number needs to be a minimum of 4 digits to 6 digits long");
        }

        if (!isNotEmpty.test(flight.getOriginAirport()) || !isNotEmpty.test(flight.getDestinationAirport())
                || flight.getOriginAirport().length() != 3 || flight.getDestinationAirport().length() != 3) {
            throw new InvalidInputException("Values empty or not exactly 3 characters in length i.e. PHL");
        }

        if (flight.getSeatCount() < 0) {
            throw new InvalidInputException("Seatcount cannot be less than 0");
        }

    }

    public void validateFullFlight(Flight flight) throws InvalidInputException {
        validateMinFlight(flight);

        LocalDateTime currentDate = LocalDateTime.now();
        if (flight.getTimeDeparture().isBefore(currentDate)) {
            throw new InvalidInputException("Time of departure must be before the current date");
        }

        if (flight.getTimeArrival().isBefore(flight.getTimeDeparture())) {
            throw new InvalidInputException("Time arrival is before time of departure");
        }

        if (flight.getPilot() < 100000 || flight.getPilot() > 999999) {
            throw new InvalidInputException("Pilot IDs are 6 digits long");
        }

        if (flight.getAirline() < 1000 || flight.getAirline() > 9999) {
            throw new InvalidInputException("Airlines are 4 digits long");
        }
    }

}
