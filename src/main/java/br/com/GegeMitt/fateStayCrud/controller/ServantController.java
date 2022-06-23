package br.com.GegeMitt.fateStayCrud.controller;

import br.com.GegeMitt.fateStayCrud.model.ServantModel;
import br.com.GegeMitt.fateStayCrud.service.ServantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servant/v1")
public class ServantController {

    @Autowired
    private ServantService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ServantModel findById(@PathVariable long id){
        ServantModel model = service.findById(id);
        buildEntityLink(model);
        return model;
    }

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public CollectionModel<ServantModel> findAll(){
        CollectionModel<ServantModel> collectionModel =
                CollectionModel.of(service.findAll());
        buildCollectionLink(collectionModel);
        return collectionModel;
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ServantModel save(@RequestBody ServantModel model){
        return service.save(model);
    }

    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ServantModel update(@RequestBody ServantModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildEntityLink(ServantModel model){
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ServantController.class).findById(model.getId())
                ).withRel(IanaLinkRelations.SELF)
        );

        if(!model.getClassModel().hasLinks()) {
            model.getClassModel().add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder
                                    .methodOn(ClassController.class)
                                    .findById(model.getClassModel().getId())
                    ).withRel(IanaLinkRelations.SELF)
            );
        }
    }

    public void buildCollectionLink(
            CollectionModel<ServantModel> collectionModel){

        for (ServantModel servant : collectionModel){
            buildEntityLink(servant);
        }

        collectionModel.add(
                WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(
                                        ServantController.class
                                ).findAll())
                        .withRel(IanaLinkRelations.COLLECTION)
        );

    }
}
