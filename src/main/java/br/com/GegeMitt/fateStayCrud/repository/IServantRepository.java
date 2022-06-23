package br.com.GegeMitt.fateStayCrud.repository;

import br.com.GegeMitt.fateStayCrud.model.ServantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServantRepository extends JpaRepository<ServantModel, Long> {

    @Query(value = "SELECT * FROM servant order by name", nativeQuery = true)
    public List<ServantModel> findAll();

    @Query(value = "SELECT s.* from servant s, class c WHERE s.class_id = c.id AND " +
            " UPPER(c.name) LIKE UPPER(:className) ORDER BY name", nativeQuery = true)
    public List<ServantModel> findByClassName(@Param("className") String className);
}
