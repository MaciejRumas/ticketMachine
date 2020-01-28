package ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ticketmachine.model.authentication.RequestStatus;
import ticketmachine.model.User;
import ticketmachine.model.authentication.AuthenticationResponse;
import ticketmachine.repository.UserRepository;
import ticketmachine.security.JwtUtil;
import ticketmachine.security.TicketMachineUserDetailsService;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TicketMachineUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestHeader(value = "Login") String login, @RequestHeader(value = "Password") String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new RequestStatus(HttpStatus.UNAUTHORIZED, "Wrong login or password", "/login"), HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User user = userRepository.findByLogin(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
    }

}
