package exercicio.java.angular.backend.documentos.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exercicio.java.angular.backend.documentos.model.HistoricoDocumento;
import exercicio.java.angular.backend.documentos.repository.HistoricoDocumentoRepository;

@Service
public class HistoricoDocumentoService {

    @Autowired
    private HistoricoDocumentoRepository historicoRepository;

    public HistoricoDocumento criarHistoricoDocumento(HistoricoDocumento historicoDocumento) {
        return historicoRepository.save(historicoDocumento);
    }

    public List<HistoricoDocumento> buscarHistoricoDocumentosPorDocumentoId(Integer id) {
        return historicoRepository.findByDocumentoId(id);
    }
}
