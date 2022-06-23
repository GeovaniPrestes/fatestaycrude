package br.com.GegeMitt.fateStayCrud.controller;

import br.com.GegeMitt.fateStayCrud.model.ClassModel;
import br.com.GegeMitt.fateStayCrud.model.ServantModel;
import br.com.GegeMitt.fateStayCrud.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class/v1")
public class ClassController {

    @Autowired
    ClassService service;

    @GetMapping(produces = {"application/json", "application/xml"})
    public CollectionModel<ClassModel> findAll(){
        CollectionModel<ClassModel> collectionModel =CollectionModel.of(service.findAll());
        buildCollectionLink(collectionModel);
        return collectionModel;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ClassModel findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping(produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ClassModel save(@RequestBody ClassModel model){
        return service.save(model);
    }

    @PutMapping(produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ClassModel update(@RequestBody ClassModel model){
        return  service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildCollectionLink(
            CollectionModel<ClassModel> collectionModel){

        for (ClassModel classModel : collectionModel){
            buildEntityLink(classModel);
        }
    }

    public void buildEntityLink(ClassModel  model) {
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ClassController.class).findById(model.getId())
                ).withRel(IanaLinkRelations.SELF)
        );
    }

}
