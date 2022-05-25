package com.example.wineshop;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WineController {

    private final WineRepository repository;

    WineController(WineRepository repository){
        this.repository = repository;
    }

    @GetMapping("/wine")
    CollectionModel<EntityModel<Wine>> all() {

        List<EntityModel<Wine>> wines= repository.findAll().stream()
                .map(wine -> EntityModel.of(wine,
                        linkTo(methodOn(WineController.class).one(wine.getId())).withSelfRel(),
                        linkTo(methodOn(WineController.class).all()).withRel("wine")))
                .collect(Collectors.toList());

        return CollectionModel.of(wines, linkTo(methodOn(WineController.class).all()).withSelfRel());
    }

    @PostMapping("/wine")
    Wine newWine(@RequestBody Wine newWine){
        return repository.save(newWine);
    }

    @GetMapping("/wine/{id}")
    EntityModel<Wine> one(@PathVariable Long id){

            Wine wine = repository.findById(id)
                    .orElseThrow(() -> new WineNotFoundException(id));

            return EntityModel.of(wine, //
                    linkTo(methodOn(WineController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(WineController.class).all()).withRel("wine"));
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




