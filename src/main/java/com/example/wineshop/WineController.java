package com.example.wineshop;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WineController {

    private final WineRepository repository;

    WineController(WineRepository repository){
        this.repository = repository;
    }

    @GetMapping("/wine")
    List<Wine> all() {
        return repository.findAll();
    }

    @PostMapping("/wine")
    Wine newWine(@RequestBody Wine newWine){
        return repository.save(newWine);
    }

    @GetMapping("/wine/{id}")
    Wine one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));
    }

    @PutMapping("/wine/{id}")
    Wine replaceWine(@RequestBody Wine newWine, @PathVariable Long id) {

        return repository.findById(id)
                .map(wine -> {
                    wine.setWine(newWine.getWine());
                    wine.setAcidity(newWine.getAcidity());
                    wine.setBody(newWine.getBody());
                    wine.setCountry(newWine.getCountry());
                    wine.setNum_reviews(newWine.getNum_reviews());
                    wine.setYear(newWine.getYear());
                    wine.setPrice(newWine.getPrice());
                    wine.setRegion(newWine.getRegion());
                    wine.setType(newWine.getType());
                    wine.setWinery(newWine.getWinery());
                    return repository.save(wine);
                })//
                .orElseGet(() -> {
                    newWine.setId(id);
                    return repository.save(newWine);
                });
    }

    @DeleteMapping("/wine/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}




