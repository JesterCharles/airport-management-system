package com.revature.ams.Member;

import com.revature.ams.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;

// TODO: REVIEW ME
public class MemberService implements Serviceable<Member> {
    private List<Member> memberList = new ArrayList<>();

    @Override
    public Member[] findAll() {
        return new Member[0];
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

    public Member findByEmailAndPassword(String email, String password){
        for (Member member : memberList) {
            if (member.getEmail().equals(email) && member.getPassword().equals(password)) {
                return member;
            }
        }
        return null;
    }

    public void update(Member updatedMember) {
        int index = -1;
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId() == updatedMember.getMemberId()) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            memberList.set(index, updatedMember);
        }
    }

}
