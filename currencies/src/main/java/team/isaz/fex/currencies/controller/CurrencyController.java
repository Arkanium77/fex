package team.isaz.fex.currencies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.isaz.fex.currencies.domain.Currency;
import team.isaz.fex.currencies.service.CurrencyService;

@RestController
public class CurrencyController {
    CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/currencies/get/name/{name}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String name) {
        try {
            return new ResponseEntity<>(service.getCurrency(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/currencies/get/id/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.getCurrency(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/secured/basic/currencies/create")
    public ResponseEntity<Currency> createCurrency(@RequestParam String name, @RequestParam Double rate) {
        try {
            return new ResponseEntity<>(service.createCurrency(name, rate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/secured/basic/currencies/create/rate_to/{rateTo}")
    public ResponseEntity<Currency> createCurrencyRatedTo(@RequestParam String name, @RequestParam Double rate, @PathVariable String rateTo) {
        try {
            return new ResponseEntity<>(service.createCurrency(name, rateTo, rate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/secured/basic/currencies/delete/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Integer id) {
        try {
            service.deleteCurrency(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
