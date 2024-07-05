package com.revature.ams.Member;

import com.revature.ams.util.interfaces.Serviceable;
import com.revature.ams.Member.Member;

public class MemberService implements Serviceable<Member> {
    @Override
    public Member[] findAll() {
        return new Member[0];
    }

    @Override
    public Member findById(int memberId) {
        return null;
    }

    @Override
    public Member create(Member newMember) {
        return null;
    }
}
