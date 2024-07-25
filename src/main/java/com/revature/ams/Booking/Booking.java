package com.revature.ams.Booking;

import com.revature.ams.Booking.dtos.BookingRequestDTO;
import com.revature.ams.Flight.Flight;
import com.revature.ams.Member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @ManyToOne
    @JoinColumn(name = "flight_number")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean carryOnAllowed;
    private short checkedLuggage;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10) default 'SEATSOPTIONAL'")
    private SeatType seatType;
    private BigDecimal price;

    public enum SeatType{
        SEATSOPTIONAL,ECONOMY,BUSINESS,FIRSTCLASS
    }

    /**
     *
     * @param bookingRequestDTO a bookingRequestDTO posted from the user
     * @output from the limited fields in BookingRequestDTO, create a Flight and Member object that Booking requires
     * this.flight and this.member are only populated with IDs at this stage
     */
    public Booking(BookingRequestDTO bookingRequestDTO){
        Flight flight = new Flight();
        flight.setFlightNumber(bookingRequestDTO.getFlightNumber());
        this.flight = flight;
        Member member = new Member();
        member.setMemberId(bookingRequestDTO.getMemberId());
        this.member = member;
        this.checkedLuggage = bookingRequestDTO.getCheckedLuggage();
        this.seatType = SeatType.valueOf(bookingRequestDTO.getSeatType());
    }

}
