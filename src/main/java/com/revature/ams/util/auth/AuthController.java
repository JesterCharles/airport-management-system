package com.revature.ams.util.auth;

import com.revature.ams.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.sasl.AuthenticationException;

@CrossOrigin(exposedHeaders = {"memberId", "memberType"}, origins = {"http://localhost:5173/", "http://ams-aws-frontend.s3-website.us-east-2.amazonaws.com/"})
@RestController
@RequestMapping("/auth")
public class AuthController  {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    private RedirectView getRedirect() {
        String url = "https://i.pinimg.com/736x/6a/6d/11/6a6d1124cf69e5588588bc7e397598f6.jpg";
        return new RedirectView(url);
    }

    @PostMapping
    private ResponseEntity<Void> postLogin(@RequestParam String email, @RequestParam String password) throws AuthenticationException {
        Member member = authService.login(email, password);
        return ResponseEntity.noContent()
                .header("memberId", String.valueOf(member.getMemberId()))
                .header("memberType", member.getType().name())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException(AuthenticationException ae){
        return ae.getMessage();
    }
}
