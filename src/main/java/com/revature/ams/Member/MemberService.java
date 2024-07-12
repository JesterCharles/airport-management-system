package com.revature.ams.Member;

import com.revature.ams.util.exceptions.DataNotFoundException;
import com.revature.ams.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;

// TODO: REVIEW ME
public class MemberService implements Serviceable<Member> {
    private List<Member> memberList = new ArrayList<>();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public Member findById(int memberId) {
        for (Member member : memberList) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    @Override
    public Member create(Member newMember) {
        memberList.add(newMember);
        return newMember;
    }

    /**
     * Searches the database for information where the email & password provided must be equal to a row within
     * our database.
     *
     * @param email - String
     * @param password - String
     * @return - Member object, if no member found it will return null
     */
    public Member findByEmailAndPassword(String email, String password){
        return memberRepository.findByEmailAndPassword(email, password);
    }

    /**
     * Update method takes in a Member Object with the updated information. Searches the list for a matching
     * memberId. Once found it replaces the object at the index with matching ids with the updated Member.
     * IF not memberId is matched, we throw an exception.
     *
     * @param updatedMember - Is an existing Member with updated information based on their request
     * @throws DataNotFoundException - MemberId provided doesn't match with anything in the database
     */
    public void update(Member updatedMember) {
//        int index = -1;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId() == updatedMember.getMemberId()) {
//                index = i;
                memberList.set(i, updatedMember);
                return;
            }
        }

        throw new DataNotFoundException("Member with ID provided not within Database");

//        if (index != -1) {
//            memberList.set(index, updatedMember);
//        }
    }

}
