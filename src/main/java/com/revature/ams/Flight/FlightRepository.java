package com.revature.ams.Flight;

import com.revature.ams.util.ConnectionFactory;
import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.exceptions.InvalidInputException;
import com.revature.ams.util.interfaces.Crudable;
import com.revature.ams.util.interfaces.Serviceable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Flight repository follows the Data Access Object (DAO) pattern
 */
public class FlightRepository implements Crudable<Flight>{

    // TODO: IMPLEMENT ME!!!!!
    @Override
    public boolean update(Flight updatedFlight) {
        return false;
    }

    // TODO: IMPLEMENT ME!!!!!
    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public List<Flight> findAll() {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Flight> flights = new ArrayList<>();

            String sql = "select * from flights";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while(rs.next()){

                flights.add(generateFlightFromResultSet(rs));
            }

            return flights;
        } catch (SQLException e) {
           e.printStackTrace();
           return null;
        }
    }

    @Override
    public Flight create(Flight newFlight) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            // DO NOT USE statement for any DML
//            String sql = "insert into flights(flight_number, origin_airport, destination_airport, seat_count) values ("
//                    + newFlight.getFlightNumber() + "," + newFlight.getOriginAirport() + "," + ;
//            Statement statement = conn.createStatement();

            String sql = "insert into flights(flight_number, origin_airport, destination_airport, seat_count) values (?,?,?,?)";
            // PreparedStatements SANITIZE any user input before execution
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // DO NOT FORGET SQL is 1-index, not 0-index. They made preparedStatement 1-index
            preparedStatement.setInt(1, newFlight.getFlightNumber());
            preparedStatement.setString(2, newFlight.getOriginAirport());
            preparedStatement.setString(3, newFlight.getDestinationAirport());
            preparedStatement.setShort(4, newFlight.getSeatCount());


            int checkInsert = preparedStatement.executeUpdate();
            System.out.println("Inserting information....");
            if (checkInsert == 0){
                throw new RuntimeException("Flight was not inserted into the datbase");
            }

            return newFlight;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Flight findById(int number) {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "select * from flights where flight_number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, number);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new DataNotFoundException("No flight with that id " + number + " exists in our database.");
            }

            return generateFlightFromResultSet(resultSet);

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Flight generateFlightFromResultSet(ResultSet rs) throws SQLException {
        Flight flight = new Flight();

        flight.setFlightNumber(rs.getInt("flight_number"));
        flight.setOriginAirport(rs.getString("origin_airport"));
        flight.setDestinationAirport(rs.getString("destination_airport"));
        flight.setSeatCount(rs.getShort("seat_count"));
        flight.setPilot(rs.getInt("pilot"));

        return flight;
    }

}
