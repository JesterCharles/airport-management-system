package com.revature.ams.Member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testMemberSaved(){
        Member member = new Member();
        member.setFirstName("Steve");
        member.setLastName("Erwin");
        member.setEmail("erwin@mail.com");
        member.setPassword("Pass123!");
        member.setType(Member.MemberType.PASSENGER);

        Member savedMember = memberRepository.save(member);
        Assertions.assertEquals(1, savedMember.getMemberId());
        Assertions.assertEquals("PASSENGER", savedMember.getType().name());
    }
}
