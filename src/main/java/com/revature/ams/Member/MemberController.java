package com.revature.ams.Member;

// TODO: REVIEW ME

import com.revature.ams.util.exceptions.DataNotFoundException;

import java.util.Scanner;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}


