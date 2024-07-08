package com.revature.ams.Flight;

import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.exceptions.InvalidInputException;
import com.revature.ams.util.exceptions.NoSpaceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTestSuite {

    private FlightService sut;

    @BeforeEach
    public void setUp(){
        sut = new FlightService();
    }

    @Test
    public void testCreate_ValidMinData() throws InvalidInputException {
        // AAA
        // Arrange - what do you need? Any objects created for testing purposes?
        Flight validFlight = new Flight(123456, "PHL", "BOS", (short) 123);

        // Action - the method invocation
        Flight returnedFlight = sut.create(validFlight);
        sut.validateMinFlight(validFlight);

        // Assert - making sure the action returns as excepted
        assertEquals(validFlight, returnedFlight);

    }

    // TDD - Test Driven Development

    /*
        Write your tests prior to implementation!
        100% Test Coverage
        You write tests before you ever write ANY implementation
     */

    @Test
    public void testCreate_InvalidData(){
        Flight invalidFlight = null;

        assertThrows(InvalidInputException.class, ()->{
            sut.create(invalidFlight);
        });
    }

    @Test
    public void testFindAll_EmptyArray() {
        // No arrange needed as flights array is empty by default

        Assertions.assertThrows(DataNotFoundException.class, () -> sut.findAll());
    }

    @Test
    public void testFindAll_NotEmptyArray() throws InvalidInputException {

        Flight validFlight = new Flight(123456, "PHL", "BOS", (short) 123);

        sut.create(validFlight);
        Flight[] retrievedFlights = sut.findAll();

        Assertions.assertEquals(5, retrievedFlights.length);
        Assertions.assertEquals(validFlight, retrievedFlights[0]);
    }


    @Test
    public void testCreate_FullArray() throws Exception {

        Flight validFlight = new Flight(123456, "PHL", "BOS", (short) 123);
        int validFlightNumber = 123457;
        short validSeatCount = 124;
        // Fill the flight array with dummy flights
        for (int i = 0; i < 5; i++) {

            sut.create( new Flight(validFlightNumber, "PHL", "BOS", validSeatCount));
            validFlightNumber++;
            validSeatCount++;
        }

        Assertions.assertThrows(NoSpaceException.class, () -> sut.create(validFlight));
    }

    @Test
    public void testCreate_InvalidFlight_Null() {
        Assertions.assertThrows(InvalidInputException.class, () -> sut.create(null));
    }

    @Test
    public void testCreate_InvalidFlight_FlightNumber() throws Exception {
        Flight invalidFlightNumber = new Flight(123, "LAX", "JFK", (short) 123);

        Assertions.assertThrows(InvalidInputException.class, () -> sut.create(invalidFlightNumber));
    }

    @Test
    public void testCreate_InvalidFlight_OriginAirport() throws Exception {
        Flight invalidOriginAirport = new Flight(56789, null, "JFK", (short) 123);

        Assertions.assertThrows(InvalidInputException.class, () -> sut.create(invalidOriginAirport));
    }

    @Test
    public void testCreate_InvalidFlight_DestinationAirport() throws Exception {
        Flight invalidDestinationAirport = new Flight(56789, "LAX", null, (short) 123);

        Assertions.assertThrows(InvalidInputException.class, () -> sut.create(invalidDestinationAirport));
    }

    @Test
    public void testCreate_InvalidFlight_AirportLength() throws Exception {
        Flight invalidAirportLength = new Flight(56789, "LAX", "JK", (short) 123);

        Assertions.assertThrows(InvalidInputException.class, () -> sut.create(invalidAirportLength));
    }
}
