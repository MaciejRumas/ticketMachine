package ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import ticketmachine.model.authentication.RequestStatus;
import ticketmachine.model.Discount;
import ticketmachine.model.Duration;
import ticketmachine.model.Ticket;
import ticketmachine.model.User;
import ticketmachine.model.Zone;
import ticketmachine.repository.DiscountRepository;
import ticketmachine.repository.DurationRepository;
import ticketmachine.repository.TicketRepository;
import ticketmachine.repository.UserRepository;
import ticketmachine.repository.ZoneRepository;

@RestController
public class TicketController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DurationRepository durationRepository;

    @GetMapping(value = "/ticket/user/{pesel}")
    public ResponseEntity<?> getAllTicketsForUser(@PathVariable String pesel) {
        Optional<User> user = userRepository.findById(pesel);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(ticketRepository.findAllByUser(user.get()));
        } else return new ResponseEntity<>(new RequestStatus(HttpStatus.NOT_FOUND, "There is no such user with " + pesel + " pesel", "/ticket/user/{pesel}"), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/ticket/{id}")
    public ResponseEntity<?> getTicketById(@PathVariable long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return ResponseEntity.ok().body(ticket.get());
        } else return new ResponseEntity<>(new RequestStatus(HttpStatus.NOT_FOUND, "Ticket with " + id + " doesn't exist", "/ticket/{id}"),HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/ticket/{id}")
    public ResponseEntity<?> deleteTicketById(@PathVariable long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketRepository.delete(ticket.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(new RequestStatus(HttpStatus.NOT_FOUND, "Ticket with " + id + " doesn't exist", "/ticket/{id}"), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/ticket")
    public ResponseEntity<?> addTicketForUser(@RequestBody Map<String, String> body) {
        try {
            Optional<User> user = userRepository.findById(body.get("user_pesel"));
            Optional<Zone> zone = zoneRepository.findById(Long.parseLong(body.get("zone_id")));
            Optional<Discount> discount = discountRepository.findById(Long.parseLong(body.get("discount_id")));
            Optional<Duration> duration = durationRepository.findById(Long.parseLong(body.get("duration_id")));
            if (user.isPresent() && zone.isPresent() && discount.isPresent() && duration.isPresent()) {
                Ticket ticket = new Ticket(user.get(), zone.get(), discount.get(), duration.get());
                ticket.calcPrice();
                return ResponseEntity.ok().body(ticketRepository.save(ticket));
            } else return new ResponseEntity<>(new RequestStatus(HttpStatus.BAD_REQUEST, "Missing data in body", "/ticket"), HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new RequestStatus(HttpStatus.BAD_REQUEST, "Wrong data type in body", "/ticket"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/ticket/{id}")
    public ResponseEntity<?> updateTicketForUser(@PathVariable long id, @RequestBody Map<String, String> body) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            try {
                Optional<User> user = userRepository.findById(body.get("user_pesel"));
                Optional<Zone> zone = zoneRepository.findById(Long.parseLong(body.get("zone_id")));
                Optional<Discount> discount = discountRepository.findById(Long.parseLong(body.get("discount_id")));
                Optional<Duration> duration = durationRepository.findById(Long.parseLong(body.get("duration_id")));
                if (user.isPresent() && zone.isPresent() && discount.isPresent() && duration.isPresent()) {
                    ticket.get().setUser(user.get());
                    ticket.get().setZone(zone.get());
                    ticket.get().setDiscount(discount.get());
                    ticket.get().setDuration(duration.get());
                    ticket.get().calcPrice();
                    return ResponseEntity.ok().body(ticketRepository.save(ticket.get()));
                } else return new ResponseEntity<>(new RequestStatus(HttpStatus.BAD_REQUEST, "Missing data in body", "/ticket/{id}"), HttpStatus.BAD_REQUEST);
            } catch (NumberFormatException e) {
                return new ResponseEntity<>(new RequestStatus(HttpStatus.BAD_REQUEST, "Wrong data type in body", "/ticket/{id}"), HttpStatus.BAD_REQUEST);
            }
        } else return new ResponseEntity<>(new RequestStatus(HttpStatus.NOT_FOUND, "Ticket with " + id + " doesn't exist", "/ticket/{id}"), HttpStatus.NOT_FOUND);
    }

}