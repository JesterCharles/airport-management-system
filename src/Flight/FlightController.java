package Flight;

import java.time.LocalDateTime;
import java.util.Scanner;

public class FlightController {
    // Attributes

    public Scanner scanner;
    private final FlightService flightService;

    // Constructors - Dependency Injection - any dependent objects are provided at initiliazation
    public FlightController(Scanner scanner, FlightService flightService) {
        this.scanner = scanner;
        this.flightService = flightService;
    }

    // Method
    public void getFlightInfo() {
        Flight[] flights = flightService.getFlightInfo();
        if(flights != null){
            for (int i = 0; i < flights.length; i++) {
                if (flights[i] != null) { // incase any random nulls in the array, we won't print out null
                    System.out.println(flights[i].toString());
                }
            }
        }
    }

    public void addFlight() {
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
        flightService.createFlight(flightToAdd);
    }
    // TODO: Implement a method to update flight information by ID
    public void updateFlightInformation(){
        Flight flightToUpdate;

        System.out.println("Please enter flight to update, starting with Flight Number: ");
        int flightNumber = scanner.nextInt();
        scanner.nextLine();

        flightToUpdate = flightService.findById(flightNumber);
        if(flightToUpdate == null) {
            System.out.println("Flight not found");
            return;
        }

        System.out.println("Enter time of departure date & time formatted as \"2024-07-03 15:48:00\" ");
        String timeDeparture = scanner.nextLine();

        System.out.println("Enter time of arrival date & time formatted as \"2024-07-03 15:48:00\" ");
        String timeArrival = scanner.nextLine();

        System.out.println("Enter pilot ID: ");
        int pilot = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter airline ID: ");
        int airline = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("Flight update status: ", flightService.updateFlight(flightToUpdate, timeArrival, timeDeparture, pilot, airline));
    }



}
