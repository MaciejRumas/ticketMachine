package ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import ticketmachine.model.Discount;
import ticketmachine.model.Duration;
import ticketmachine.model.Validity;
import ticketmachine.model.Zone;
import ticketmachine.repository.DiscountRepository;
import ticketmachine.repository.DurationRepository;
import ticketmachine.repository.ValidityRepository;
import ticketmachine.repository.ZoneRepository;

@RestController
public class InfoController {

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ValidityRepository validityRepository;

    @Autowired
    private DurationRepository durationRepository;

    @GetMapping(value = "/zone")
    public ResponseEntity<List<Zone>> getAllZones() {
        return ResponseEntity.ok().body(zoneRepository.findAll());
    }

    @GetMapping(value = "/discount")
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok().body(discountRepository.findAll());
    }

    @GetMapping(value = "/validity")
    public ResponseEntity<List<Validity>> getAllValidity() {
        return ResponseEntity.ok().body(validityRepository.findAll());
    }

    @GetMapping(value = "/duration")
    public ResponseEntity<List<Duration>> getAllDurations() {
        return ResponseEntity.ok().body(durationRepository.findAll());
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> home() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
