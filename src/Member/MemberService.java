package Member;

import util.interfaces.Serviceable;
import Member.Member;

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
    public void create(Member newMember) {

    }
}
