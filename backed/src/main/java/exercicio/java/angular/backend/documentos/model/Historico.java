package exercicio.java.angular.backend.documentos.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historico_documentos")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "documentos_id", referencedColumnName = "id")
    private Documento documento;

    private LocalDateTime data;

    private String mudanca;
}