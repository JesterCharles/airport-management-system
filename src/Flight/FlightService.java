package Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Predicate;

// TODO: Implement the Service Layer, this contains all Business Logic (anything that makes sense in the scope of a business)
public class FlightService {
    private Flight[] flights = new Flight[5];
    // -> lamba: format () -> {}, defining any parameteres used by the function and it's execution. Parenthesis not necessary for oen parameter
    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();

    public Flight[] getFlightInfo(){
        if (isEmpty(flights)) {
            System.out.println("No flight info");
            return null;
        } else {
            System.out.println("Returning flight information....");
            return flights;
        }
    }

    public void createFlight(Flight flight){
        validateMinFlight(flight);
        int indexToReplace = getFirstNull(flights);
        if (indexToReplace == -1) {
            System.out.println("Sorry, our flight database is full, please try again later");
        }  else {
            flights[indexToReplace] = flight;
            System.out.printf("Flight %s successfully added\n", flight);
        }
    }

    public Flight findById(int flightNumber){
        for (Flight flight : flights) {
            if (flight != null && flight.getFlightNumber()== flightNumber) {
                return flight;
            }
        }

        return null;
    }


    public boolean updateFlight(Flight flightToUpdate, String timeArrival, String timeDeparture, int pilot, int airline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        flightToUpdate.setTimeArrival(LocalDateTime.parse(timeArrival, formatter));
        flightToUpdate.setTimeDeparture(LocalDateTime.parse(timeDeparture, formatter));
        flightToUpdate.setPilot(pilot);
        flightToUpdate.setAirline(airline);

        validateFullFlight(flightToUpdate);

        return true;
    }


    private boolean isEmpty(Flight[] arr) { // defining the parameter of a string array to be included when executing this mehtod
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

    private void validateMinFlight(Flight flight) {
        if (flight == null) {
            throw new RuntimeException();
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
            throw new RuntimeException();
        }

        if (!isNotEmpty.test(flight.getOriginAirport()) || !isNotEmpty.test(flight.getDestinationAirport())
                || flight.getOriginAirport().length() != 3 || flight.getDestinationAirport().length() != 3) {
            throw new RuntimeException();
        }

        if (flight.getSeatCount() < 0) {
            throw new RuntimeException();
        }

    }

    private void validateFullFlight(Flight flight) {
        validateMinFlight(flight);

        LocalDateTime currentDate = LocalDateTime.now();
        if (flight.getTimeDeparture().isBefore(currentDate)) {
            throw new RuntimeException();
        }

        if (flight.getTimeArrival().isBefore(flight.getTimeDeparture())) {
            throw new RuntimeException();
        }

        if (flight.getPilot() < 100000 || flight.getPilot() > 999999) {
            throw new RuntimeException();
        }

        if (flight.getAirline() < 1000 || flight.getAirline() > 9999) {
            throw new RuntimeException();
        }
    }

}
