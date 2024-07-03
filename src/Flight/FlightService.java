package Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// TODO: Implement the Service Layer, this contains all Business Logic (anything that makes sense in the scope of a business)
public class FlightService {
    private Flight[] flights = new Flight[5];


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
        int indexToReplace = getFirstNull(flights);
        if (indexToReplace == -1) {
            System.out.println("Sorry, our flight database is full, please try again later");
        } else if(!validateMinFlight(flight)){
            System.out.println("Invalid information provided.");
        } else {
            flights[indexToReplace] = flight;
            System.out.printf("Flight %s successfully added\n", flight);
        }
    }

    public Flight findById(int flightNumber){
        for (Flight flight : flights) {
            if (flight != null && flight.flightNumber == flightNumber) {
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

        if(!validateFullFlight(flightToUpdate)) return false;

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

    private boolean validateMinFlight(Flight flight) {
        if (flight == null) {
            return false;
        }

        if (flight.flightNumber < 1000 || flight.flightNumber > 999999) {
            System.out.println("Invalid flight number");
            return false;
        }

        if (flight.originAirport.length() != 3 || flight.destinationAirport.length() != 3) {
            System.out.println("Invalid airport code");
            return false;
        }

        if (flight.seatCount < 0) {
            System.out.println("Invalid seat count");
            return false;
        }

        return true;
    }

    private static boolean validateFullFlight(Flight flight) {
        if (flight == null) {
            return false;
        }
        if (flight.flightNumber < 1000 || flight.flightNumber > 999999) {
            System.out.println("Invalid flight number");
            return false;
        }

        if (flight.originAirport.length() != 3 || flight.destinationAirport.length() != 3) {
            System.out.println("Invalid airport code");
            return false;
        }

        if (flight.seatCount < 0) {
            System.out.println("Invalid seat count");
            return false;
        }

        LocalDateTime currentDate = LocalDateTime.now();
        if (flight.timeDeparture.isBefore(currentDate)) {
            System.out.println("Invalid departure time");
            return false;
        }

        if (flight.timeArrival.isBefore(flight.timeDeparture)) {
            System.out.println("Invalid arrival time");
            return false;
        }

        if (flight.pilot < 100000 || flight.pilot > 999999) {
            System.out.println("Invalid pilot ID");
            return false;
        }

        if (flight.airline < 1000 || flight.airline > 9999) {
            System.out.println("Invalid airline ID");
            return false;
        }

        return true;
    }

}
