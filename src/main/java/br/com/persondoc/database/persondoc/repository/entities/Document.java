package br.com.persondoc.database.persondoc.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENT")
    @Column(name = "ID_DOCUMENT")
    private Long idNumber;

    @Column(name = "DT_TYPE")
    private String documentType;

    @Column(name = "NUM_DOCUMENT")
    private String documentNumber;

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getDocumentType() {return documentType;}

    public void setDocumentType(String documentType) {this.documentType = documentType;}

    public String getDocumentNumber(){return documentNumber;}

    public void setDocumentNumber(String documentNumber){this.documentNumber = documentNumber;}
}
