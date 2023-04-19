package exercicio.java.angular.backend.documentos.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exercicio.java.angular.backend.documentos.model.HistoricoDocumento;
import exercicio.java.angular.backend.documentos.service.impl.HistoricoDocumentoService;


@RestController
@RequestMapping("/historico-documentos")
public class HistoricoDocumentoController {

    @Autowired
    private HistoricoDocumentoService historicoDocumentoService;

    @PostMapping
    public ResponseEntity<HistoricoDocumento> criarHistoricoDocumento(@RequestBody HistoricoDocumento historicoDocumento) {
        HistoricoDocumento novoHistoricoDocumento = historicoDocumentoService.criarHistoricoDocumento(historicoDocumento);
        return ResponseEntity.ok(novoHistoricoDocumento);
    }

    @GetMapping("/{documentoId}")
    public ResponseEntity<List<HistoricoDocumento>> buscarHistoricoDocumentosPorDocumentoId(@PathVariable Integer documentoId) {
        List<HistoricoDocumento> historicoDocumentos = historicoDocumentoService.buscarHistoricoDocumentosPorDocumentoId(documentoId);
        return ResponseEntity.ok(historicoDocumentos);
    }
}