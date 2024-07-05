package com.revature.ams.Flight;

import com.revature.ams.util.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
