package com.revature.ams.Booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // TODO: Figure out what's going wrong
    @Query("FROM Booking b WHERE b.member.memberId = :memberId")
    Optional<List<Booking>> findAllBookingsByMemberId(int memberId);
}
