package exercicio.java.angular.backend.documentos.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exercicio.java.angular.backend.documentos.model.HistoricoDocumento;

@Repository
public interface HistoricoDocumentoRepository extends JpaRepository<HistoricoDocumento, Integer> {

    List<HistoricoDocumento> findByDocumentoId(Integer id);

}