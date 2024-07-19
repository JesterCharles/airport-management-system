package com.revature.ams.Booking;

import com.revature.ams.Booking.dtos.BookingRequestDTO;
import com.revature.ams.Flight.Flight;
import com.revature.ams.Member.Member;

import java.math.BigDecimal;

public class Booking {
    private int bookingId;
    private Flight flight;
    private Member member;
    private boolean carryOnAllowed;
    private short checkedLuggage;
    private SeatType seatType;
    private BigDecimal price;

    public enum SeatType{
        SEATSOPTIONAL,ECONOMY,BUSINESS,FIRSTCLASS
    }

    public Booking() {
    }

    public Booking(int bookingId, Flight flight, Member member, boolean carryOnAllowed, short checkedLuggage, SeatType seatType, BigDecimal price) {
        this.bookingId = bookingId;
        this.flight = flight;
        this.member = member;
        this.carryOnAllowed = carryOnAllowed;
        this.checkedLuggage = checkedLuggage;
        this.seatType = seatType;
        this.price = price;
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

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isCarryOnAllowed() {
        return carryOnAllowed;
    }

    public void setCarryOnAllowed(boolean carryOnAllowed) {
        this.carryOnAllowed = carryOnAllowed;
    }

    public short getCheckedLuggage() {
        return checkedLuggage;
    }

    public void setCheckedLuggage(short checkedLuggage) {
        this.checkedLuggage = checkedLuggage;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = SeatType.valueOf(seatType);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", flight=" + flight +
                ", member=" + member +
                ", carryOnAllowed=" + carryOnAllowed +
                ", checkedLuggage=" + checkedLuggage +
                ", seatType=" + seatType +
                ", price=" + price +
                '}';
    }
}
