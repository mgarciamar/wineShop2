package com.example.wineshop;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
    private final WineModelAssembler assembler;

    WineController(WineRepository repository, WineModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/wine")
    CollectionModel<EntityModel<Wine>> all() {

        List<EntityModel<Wine>> wines = repository.findAll().stream()
                .map(wine -> EntityModel.of(wine,
                        linkTo(methodOn(WineController.class).one(wine.getId())).withSelfRel(),
                        linkTo(methodOn(WineController.class).all()).withRel("wine")))
                .collect(Collectors.toList());

        return CollectionModel.of(wines, linkTo(methodOn(WineController.class).all()).withSelfRel());
    }

    @PostMapping("/wine")
    ResponseEntity<?> newWine(@RequestBody Wine newWine) {

        EntityModel<Wine> entityModel = assembler.toModel(repository.save(newWine));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    @GetMapping("/wine/{id}")
    EntityModel<Wine> one(@PathVariable Long id) {

        Wine wine = repository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));

        return EntityModel.of(wine, //
                linkTo(methodOn(WineController.class).one(id)).withSelfRel(),
                linkTo(methodOn(WineController.class).all()).withRel("wine"));
    }


    @PutMapping("/wine/{id}")
    ResponseEntity<?> replaceWine(@RequestBody Wine newWine, @PathVariable Long id){
        Wine updatedWine = repository.findById(id) //
                .map(wine -> {
                    wine.setRating(newWine.getRating());
                    wine.setAcidity(newWine.getAcidity());
                    wine.setBody(newWine.getBody());
                    wine.setRating(newWine.getRating());
                    wine.setNum_reviews(newWine.getNum_reviews());
                    wine.setYear(newWine.getYear());
                    wine.setPrice(newWine.getPrice());
                    wine.setWinery_id(newWine.getWinery_id());
                    wine.setType_id(newWine.getType_id());
                    wine.setRegion_id(newWine.getRegion_id());
                    return repository.save(wine);
                })//
                .orElseGet(() -> {
                    newWine.setId(id);
                    return repository.save(newWine);
                });
        EntityModel<Wine> entityModel = assembler.toModel(updatedWine);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


    @DeleteMapping("/wine/{id}")
    ResponseEntity<?> deleteWine(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}




