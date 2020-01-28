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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ticketmachine.model.Card;
import ticketmachine.model.Discount;
import ticketmachine.model.User;
import ticketmachine.model.Validity;
import ticketmachine.model.Zone;
import ticketmachine.repository.CardRepository;
import ticketmachine.repository.DiscountRepository;
import ticketmachine.repository.UserRepository;
import ticketmachine.repository.ValidityRepository;
import ticketmachine.repository.ZoneRepository;

@RestController
public class CardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ValidityRepository validityRepository;

    @GetMapping(value = "/card/user/{pesel}")
    public ResponseEntity<List<Card>> getAllCardsForUser(@PathVariable String pesel) {
        Optional<User> user = userRepository.findById(pesel);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(cardRepository.findAllByUser(user.get()));
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/card/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            return ResponseEntity.ok().body(card.get());
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/card/{id}")
    public ResponseEntity<Void> deleteCardById(@PathVariable long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            cardRepository.delete(card.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/card")
    public ResponseEntity<Card> addCardForUser(@RequestBody Map<String, String> body) {
        try {
            Optional<User> user = userRepository.findById(body.get("user_pesel"));
            Optional<Zone> zone = zoneRepository.findById(Long.parseLong(body.get("zone_id")));
            Optional<Discount> discount = discountRepository.findById(Long.parseLong(body.get("discount_id")));
            Optional<Validity> validity = validityRepository.findById(Long.parseLong(body.get("validity_id")));
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("start_date"));
            if (user.isPresent() && zone.isPresent() && discount.isPresent() && validity.isPresent()) {
                Card card = new Card(user.get(), discount.get(), validity.get(), zone.get(), date);
                card.calcPrice();
                return ResponseEntity.ok().body(cardRepository.save(card));
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ParseException | NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/card/{id}")
    public ResponseEntity<Card> updateCardForUser(@PathVariable long id, @RequestBody Map<String, String> body) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            try {
                Optional<User> user = userRepository.findById(body.get("user_pesel"));
                Optional<Zone> zone = zoneRepository.findById(Long.parseLong(body.get("zone_id")));
                Optional<Discount> discount = discountRepository.findById(Long.parseLong(body.get("discount_id")));
                Optional<Validity> validity = validityRepository.findById(Long.parseLong(body.get("validity_id")));
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("start_date"));
                if (user.isPresent() && zone.isPresent() && discount.isPresent() && validity.isPresent()) {
                    card.get().setUser(user.get());
                    card.get().setZone(zone.get());
                    card.get().setDiscount(discount.get());
                    card.get().setDiscount(discount.get());
                    card.get().setStartDate(date);
                    card.get().calcPrice();
                    return ResponseEntity.ok().body(cardRepository.save(card.get()));
                } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (ParseException | NumberFormatException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
