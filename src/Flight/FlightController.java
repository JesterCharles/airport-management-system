package Flight;

import java.util.Scanner;

public class FlightController {
    // Attributes
    private Flight[] flights = new Flight[5];
    public Scanner scanner;

    // Constructors - Dependency Injection - any dependent objects are provided at initiliazation
    public FlightController(Scanner scanner) {
        this.scanner = scanner;
    }

    // Method
    public void getFlightInfo() {
        if (isEmpty(flights)) {
            System.out.println("No flight info");
        } else {
            for (int i = 0; i < flights.length; i++) {
                if (flights[i] != null) { // incase any random nulls in the array, we won't print out null
                    System.out.println(flights[i].toString());
                }
            }
        }
    }

    public void addFlight() {
        int indexToReplace = getFirstNull(flights);
        if (indexToReplace == -1) {
            System.out.println("Sorry, our flight database is full, please try again later");
        } else {
            Flight flightToAdd;

            System.out.println("Please enter flight info, starting with Flight Number: ");
            int flightNumber = scanner.nextInt();

            System.out.println("Enter origin airport three letter code (Ex. PHL): ");
            String originAirport = scanner.next();

            System.out.println("Enter destination airport three letter code (Ex. PHL): ");
            String destinationAirport = scanner.next();

            System.out.println("Enter number of seats: ");
            // Generally, when you cast a value you should only cast smaller to larger
            short seatCount = (short) scanner.nextInt(); // taking the int and converting it's datatype to short

            flightToAdd = new Flight(flightNumber, originAirport, destinationAirport, seatCount);

            flights[indexToReplace] = flightToAdd;
            System.out.printf("Flight %s successfully added\n", flightToAdd);
        }
    }

    // TODO: Implement a method to update flight information by ID
    public void updateFlightInformation(){

    }

    public static boolean isEmpty(Flight[] arr) { // defining the parameter of a string array to be included when executing this mehtod
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
}
