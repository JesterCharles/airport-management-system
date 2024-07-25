package com.revature.ams.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Flight repository follows the Data Access Object (DAO) pattern
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
