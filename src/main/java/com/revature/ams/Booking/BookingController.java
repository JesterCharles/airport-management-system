package com.revature.ams.Booking;

import com.revature.ams.Booking.dtos.BookingRequestDTO;
import com.revature.ams.Booking.dtos.BookingResponseDTO;
import com.revature.ams.Flight.FlightService;
import com.revature.ams.Member.MemberService;
import com.revature.ams.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * BookingController is the control layer of the booking functional group. It must be injected with bookingService,
 * memberService, and flightService dependencies at instantiation [all three are declared private final].
 * The class implements the ams.util.interfaces.Controller interface.
 */
public class BookingController implements Controller {
    private final BookingService bookingService;
    private final MemberService memberService;
    private final FlightService flightService;

    public BookingController(BookingService bookingService, MemberService memberService, FlightService flightService) {
        this.bookingService = bookingService;
        this.memberService = memberService;
        this.flightService = flightService;
    }

    /**
     * registerPaths accepts a Javalin object and is called by the front controller to create the
     * endpoints for the booking HTTP requests.
     *
     * @param app Javalin object.
     */
    @Override
    public void registerPaths(Javalin app) {
        app.post("/bookings", this::postBookFlight);
        app.get("/bookings", this::findAllBookings);
        app.get("/bookings/my", this::getMembersBookings);
        app.delete("/bookings/{booking_id}", this::deleteBooking);
    }

    /**
     * postBookFlight accepts a Context object before utilizing context.bodyAsClass to parse a
     * BookingRequestDTO object before creating a new Booking object by passing the bookingRequestDTO object.
     * The booking object is then passed to bookingService.bookFlight to create a bookingResponseDTO object.
     *
     * If the method is successful it returns a 201 (Created) status as well as a json of the bookingResponseDTO object.
     * @param ctx Jackson Context object.
     */
    private void postBookFlight(Context ctx) {
        BookingRequestDTO bookingRequestDTO = ctx.bodyAsClass(BookingRequestDTO.class);
        Booking newBooking = new Booking(bookingRequestDTO);

        newBooking.setFlight(flightService.findById(bookingRequestDTO.getFlightNumber()));
        newBooking.setMember(memberService.findById(bookingRequestDTO.getMemberId()));

        BookingResponseDTO bookingResponseDTO = bookingService.bookFlight(newBooking);
        ctx.status(201);
        ctx.json(bookingResponseDTO);
    }

    /**
     * findAllBookings accepts a Context object before parsing the header to get the memberType attribute to
     * verify that the memberType is set to ADMIN. If it is null or not ADMIN a 403 Forbidden HTTP status is returned,
     * along with a status message.
     * If the memberType is ADMIN it responds with a call to bookingService.findAll via ctx.json.
     * @param ctx Jackson Context object.
     */
    private void findAllBookings(Context ctx){
        String memberType = ctx.header("memberType");
        if(memberType == null || !memberType.equals("ADMIN")) {
            ctx.status(403);
            ctx.result("You do not have sufficient permission to perform this action, as you are not logged in as an Admin");
            return;
        }

        ctx.json(bookingService.findAll());
    }

    /**
     * getMemberBookings accepts a Context object as a parameter and verifies that the member is logged in.
     * If a member is logged in it will then call bookingService.findAllBookingsByMemberId to return the flights
     * with that specific memberId via a json response.
     * @param ctx Jackson Context object.
     */
    private void getMembersBookings(Context ctx) {
        int memberId = loggedInCheck(ctx);
        if (memberId == -1) return;

        ctx.json(bookingService.findAllBookingsByMemberId(memberId));
    }

    // TODO: Implement Me
    private void deleteBooking(Context ctx) {

    }

    /**
     * loggedInCheck accepts a Context object as a parameter and verifies that a member is logged in through its
     * header. If the memberId is null it responds with a 400 (bad request) status code and a response. Otherwise
     * it returns the memberId via Integer.parseInt.
     * @param ctx Jackson Context object.
     * @return member's memberId as it exists in the context header
     */
    private int loggedInCheck(Context ctx) {
        String headerMemberId = ctx.header("memberId");
        if (headerMemberId == null) {
            ctx.status(400);
            ctx.result("You are not logged in.");
            return -1;
        }
        return Integer.parseInt(headerMemberId);
    }

}
