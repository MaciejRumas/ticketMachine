package ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ticketmachine.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    //@PostMapping(value = "/user/login")
    //public ResponseEntity<?> login

}
