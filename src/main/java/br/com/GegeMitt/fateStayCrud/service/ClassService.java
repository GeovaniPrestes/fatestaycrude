package br.com.GegeMitt.fateStayCrud.service;

import br.com.GegeMitt.fateStayCrud.exception.NotFoundException;
import br.com.GegeMitt.fateStayCrud.model.ClassModel;
import br.com.GegeMitt.fateStayCrud.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private IClassRepository repository;

    public ClassModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }

    public List<ClassModel> findAll(){
        return repository.findAll();
    }

    public ClassModel save(ClassModel model){
        return repository.save(model);
    }

    public ClassModel update(ClassModel model){
        ClassModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setName(model.getName());
        return repository.save(found);
    }

    public void delete(long id){
        ClassModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }

}
