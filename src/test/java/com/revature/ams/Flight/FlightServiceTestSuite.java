package com.revature.ams.Flight;

import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.exceptions.InvalidInputException;
import com.revature.ams.util.exceptions.NoSpaceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FlightServiceTestSuite {

    //@Mock // indicates the object needing to be mocked
    private FlightRepository mockFlightRepository;

    //@InjectMocks // inject our FlightRepostory into our FlightService as a mocked objected
    private FlightService sut;

    @BeforeEach
    public void setUp(){
        mockFlightRepository = mock(FlightRepository.class);
        sut = new FlightService(mockFlightRepository);
    }

    @Test
    public void testCreate_ValidMinData() throws InvalidInputException {
        // AAA
        // Arrange - what do you need? Any objects created for testing purposes?
        Flight validFlight = new Flight(999998, "PHL", "BOS", (short) 123);
        when(mockFlightRepository.create(validFlight)).thenReturn(validFlight);

        // Action - the method invocation
        Flight returnedFlight = sut.create(validFlight);

        // Assert - making sure the action returns as excepted
        assertEquals(validFlight, returnedFlight);
        verify(mockFlightRepository, times(1)).create(validFlight);

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

    @Test
    public void testUpdate_ValidFlight(){
        // AAA
        Flight validFlight = new Flight(123456, "PHL", "BOS", OffsetDateTime.now().plusHours(2),
                OffsetDateTime.now().plusDays(1), (short) 123, 123456, 1234);
        when(mockFlightRepository.update(validFlight)).thenReturn(true);

        boolean returnedUpdate = sut.update(validFlight);
        assertTrue(returnedUpdate);
        verify(mockFlightRepository, times(1)).update(validFlight);
    }

    @Test
    public void testDelete_validFlightNumber(){
        Flight validFlight = new Flight(123456, "PHL", "BOS", OffsetDateTime.now().plusHours(2),
                OffsetDateTime.now().plusDays(1), (short) 123, 123456, 1234);
        when(mockFlightRepository.delete(validFlight)).thenReturn(true);

        boolean actual = sut.delete(validFlight);

        assertTrue(actual);
        verify(mockFlightRepository, times(1)).delete(validFlight);
    }
}
