package br.com.persondoc.database.persondoc.repository.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "TB_PERSON")
@GenericGenerator(
        name = "SEQ_PERSON",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_PERSON"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    @Column(name = "ID_PERSON")
    private Long id;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DT_AGE")
    private Long age;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getId(){ return id; }

    public void setId(Long id) {this.id = id; }

    public Long getAge(){ return age; }

    public void setAge(Long age) {this.age = age; }

}
