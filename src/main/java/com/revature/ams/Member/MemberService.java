package com.revature.ams.Member;

import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Service
public class MemberService implements Serviceable<Member> {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Member findById(int memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new DataNotFoundException("Nothing in the database with ID of " + memberId));
    }

    @Override
    public Member create(Member newMember) {
        newMember.setType(Member.MemberType.valueOf("PASSENGER"));
        return memberRepository.save(newMember);
    }

    @Override
    public boolean delete(Member removedMemeber) {
        memberRepository.delete(removedMemeber);
        return true;
    }

    /**
     * Searches the database for information where the email & password provided must be equal to a row within
     * our database.
     *
     * @param email - String
     * @param password - String
     * @return - Member object, if no member found it will return null
     */
    public Member findByEmailAndPassword(String email, String password) throws AuthenticationException {
        return memberRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new AuthenticationException("Invalid credentials provided"));
    }

    /**
     * Update method takes in a Member Object with the updated information. Searches the list for a matching
     * memberId. Once found it replaces the object at the index with matching ids with the updated Member.
     * IF not memberId is matched, we throw an exception.
     *
     * @param updatedMember - Is an existing Member with updated information based on their request
     * @throws DataNotFoundException - MemberId provided doesn't match with anything in the database
     */
    public boolean update(Member updatedMember) {
        memberRepository.save(updatedMember);
        return true;
    }

}
