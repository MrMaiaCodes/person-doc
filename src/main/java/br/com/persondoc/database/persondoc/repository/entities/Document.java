package br.com.persondoc.database.persondoc.repository.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "TB_DOCUMENT")
@GenericGenerator(
        name = "SEQ_DOCUMENT",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_DOCUMENT"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENT")
    @Column(name = "ID_DOCUMENT")
    private Long idNumber;

    @Column(name = "DS_NAME")
    private String holderName;

    @Column(name = "DT_TYPE")
    private Long documentType;

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }
}
