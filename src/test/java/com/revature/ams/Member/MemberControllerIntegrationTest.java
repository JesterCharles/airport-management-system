package com.revature.ams.Member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerIntegrationTest {
    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberController memberController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestMembersWithValidMemberThenCorrecResponse() throws Exception {
        String memberJson = "{\n" +
                "    \"firstName\": \"Tester\",\n" +
                "    \"lastName\":\"McTesterson\",\n" +
                "    \"email\":\"tester@mail.com\",\n" +
                "    \"password\":\"Pass123!\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .content(memberJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}
