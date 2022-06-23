package br.com.GegeMitt.fateStayCrud.repository;

import br.com.GegeMitt.fateStayCrud.model.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassRepository extends JpaRepository<ClassModel, Long> {
}
