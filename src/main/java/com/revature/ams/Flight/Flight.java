package com.revature.ams.Flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.revature.ams.Member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
// JPA
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @SequenceGenerator(name = "FlightNumberGen", initialValue = 100000)
    @GeneratedValue(generator = "FlightNumberGen")
    @Column(columnDefinition = "integer check (flight_number < 999999)")
    private int flightNumber;
    private String originAirport; // Airport identification number or String
    private String destinationAirport;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime timeDeparture;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime timeArrival;
    private short seatCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member pilot;
    private int airline;

    public Flight(int flightNumber, String originAirport, String destinationAirport, short seatCount) {
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.seatCount = seatCount;
    }
}
