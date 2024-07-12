package com.revature.ams.Flight;

import com.revature.ams.util.exceptions.InvalidInputException;
import com.revature.ams.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.function.Predicate;

public class FlightController implements Controller {
    // Attributes

    private final FlightService flightService;

    private Predicate<String> isNotEmpty = str -> str != null && !str.isBlank();

    // Constructors - Dependency Injection - any dependent objects are provided at initiliazation
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // One of the principles of REST is uniform interface, simply means that the PATH in your URL makes sense given the context
    // of what that path is handling

    @Override
    public void registerPaths(Javalin app) {
        app.get("/flights", this::getAllFlights);
        app.post("/flights", this::postNewFlight);
        app.get("/flights/{flightNumber}", this::getFlightById); //Path Parameter
        app.put("flights", this::putUpdateFlight);
    }


    public void getAllFlights(Context ctx){
        List<Flight> flights = flightService.findAll();
        ctx.json(flights);
    }

    public void postNewFlight(Context ctx){
        Flight flight = ctx.bodyAsClass(Flight.class); // request body & mapping from JSON to Java Object

        ctx.json(flightService.create(flight)); // Respond with the created flight
        ctx.status(HttpStatus.CREATED); // Responded with a successfull status code
    }

    private void getFlightById(Context ctx) {
        int flightNumber = Integer.parseInt(ctx.pathParam("flightNumber"));
        Flight foundFlight = flightService.findById(flightNumber);

        ctx.json(foundFlight);
    }


    private void putUpdateFlight(Context ctx) {
        Flight updatedFlight = ctx.bodyAsClass(Flight.class);

        try {
            if (flightService.update(updatedFlight)) {
                ctx.status(HttpStatus.ACCEPTED);
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e){
            e.printStackTrace();
        }
    }
}
