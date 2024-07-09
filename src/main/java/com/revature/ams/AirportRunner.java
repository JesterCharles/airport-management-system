package com.revature.ams;

import com.revature.ams.Flight.FlightController;
import com.revature.ams.Flight.FlightService;
import com.revature.ams.Member.Member;
import com.revature.ams.Member.MemberController;
import com.revature.ams.Member.MemberService;
import com.revature.ams.util.auth.AuthController;
import com.revature.ams.util.auth.AuthService;

import java.util.Scanner;

// FrontController Example
public class AirportRunner {
    public static void main(String[] args) {
        System.out.println("Airport Management System is up and running.....");


        // Variables that need to be at the top we need to declare earlier
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        FlightService flightService = new FlightService();
        FlightController flightController = new FlightController(scanner, flightService); // Instiating a FlightController Object

        // TODO: REVIEW ME
        Member memberLoggedIn = null;
        MemberService memberService = new MemberService();
        MemberController memberController = new MemberController(scanner, memberService);

        AuthService authService = new AuthService(memberService);
        AuthController authController = new AuthController(scanner, authService);

        // Design Step 1
        // declaring to Java what datatype the scanner variable is
        do {

            System.out.println("Welcome to our Airport!");
            System.out.println("1. Flight Information"); // ctrl + d on a line will repeat
            System.out.println("2. Add Flights");
            System.out.println("3. Update Flight"); // sout - shorthand for System.out.println();
            // TODO: REVIEW ME
            System.out.println("4. Register");
            System.out.println("5. Update Member Info");
            System.out.println("6. Login");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.println();
            System.out.println("Enter your numeric choice from above: ");

            // Validate user's input is indeed an int
            // ! indicates if it's falses then it's condition has been met
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid Input, Please enter a number 1-3.");
                // because they entered not a number, we need to put the scanner to the next
                scanner.nextLine();
                continue; // jump back to the beginning of the do-while loop
            }

            choice = scanner.nextInt(); // can only reassign value, not datatype

//            if( choice > 3){
//                System.out.println("Invalid Input, Please enter a number 1-3.");
//                // because they entered not a number, we need to put the scanner to the next
//                scanner.nextLine();// jump back to the beginning of the do-while loop
//            }

            switch (choice) {
                case 1: // If choice == 1
                    System.out.println("Viewing flight information....");
                    flightController.getFlightInfo();
                    break; //include break, otherwise it will fall through to the next case statement
                case 2:
                    System.out.println("Adding a flight...");
                    flightController.addFlight();
                    break;
                case 3:
                    System.out.println("Updating a flight...");
                    flightController.updateFlightInformation();
                    break;
                case 4:
                    System.out.println("Registering user....");
                    memberController.register();
                    break;
                case 5:
                    System.out.println("Updating user....");
                    memberController.update();
                    break;
                case 6:
                    System.out.println("Logging In....");
                    memberLoggedIn = authController.login(memberLoggedIn);
                    if(memberLoggedIn == null) break;
                    System.out.printf("Welcome back, %s! ", memberLoggedIn.getFirstName());
                    break;
                case 7:
                    System.out.println("Logging out...");
                    memberLoggedIn = null;
                    break;
                case 8:
                    System.out.println("Thanks for using our airpoirt services, have a wonderful day!");
                    break;
                default:
                    System.out.println("Invalid Input, Please enter a number 1-6.");

            }
            //System.out.printf("Your choice was option %s", choice); // print formater, where %s is replaced with the variable
        } while (choice != 8);
    }

}
