//package digi.ecomm.user.controller;
//
//import digi.ecomm.user.config.JwtTokenProvider;
//import digi.ecomm.user.request.LoginRequest;
//import digi.ecomm.user.response.LoginResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//public class TestController {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtTokenProvider tokenProvider;
//
//    @PostMapping("/login")
//    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUid(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.generateToken((UserDetails) authentication.getPrincipal());
//        return new LoginResponse(jwt);
//    }
//}
