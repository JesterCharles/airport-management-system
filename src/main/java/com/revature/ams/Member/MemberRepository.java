package com.revature.ams.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmailAndPassword(String email, String password);

    @Query("FROM Member where email = :email AND password = :password")
    Optional<Member> authCheck(String email, String password);
}
