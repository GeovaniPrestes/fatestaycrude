package br.com.GegeMitt.fateStayCrud.service;

import br.com.GegeMitt.fateStayCrud.exception.NotFoundException;
import br.com.GegeMitt.fateStayCrud.model.ServantModel;
import br.com.GegeMitt.fateStayCrud.repository.IServantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServantService {

    @Autowired
    private IServantRepository repository;


    public List<ServantModel> findAll(){
        return repository.findAll();
    }

    public List<ServantModel> findByAuthor(String className){
        return repository.findByClassName("%" + className + "%");
    }

    public ServantModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }

    public ServantModel save(ServantModel model){
        return repository.save(model);
    }

    public ServantModel update(ServantModel model){
        ServantModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setName(model.getName());
        return  repository.save(model);
    }

    public void delete(long id){
        ServantModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }
}
