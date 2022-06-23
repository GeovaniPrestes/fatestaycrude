package br.com.GegeMitt.fateStayCrud.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "servant")
public class ServantModel  extends RepresentationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public ServantModel() {
    }

    public ServantModel(Long id, String name, ClassModel classModel) {
        this.id = id;
        this.name = name;
        this.classModel = classModel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private ClassModel classModel;

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServantModel servantModel = (ServantModel) o;
        return id == servantModel.id && Objects.equals(name, servantModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
