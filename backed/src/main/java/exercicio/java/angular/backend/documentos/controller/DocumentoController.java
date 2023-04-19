package exercicio.java.angular.backend.documentos.controller;

import exercicio.java.angular.backend.documentos.model.Documento;
import exercicio.java.angular.backend.documentos.service.IDocumentoService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("setores/{setorId}/pastas/{pastaId}/documentos")
public class DocumentoController {

    @Autowired
    private IDocumentoService service;


    @GetMapping
    @ApiOperation(value = "Lista todos os documentos")
    public List<Documento> listAll(
            @PathVariable Long setorId,
            @PathVariable Long pastaId,
            @RequestParam(defaultValue = "") String q) {
        return service.listAll(setorId, pastaId, q);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Pesquisa Pelo Id de pastas e setores")
    public Documento findById(
            @PathVariable Long setorId,
            @PathVariable Long pastaId,
            @PathVariable Long id) {
        return service.findById(setorId, pastaId, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Insere um novo documento no banco")
    public Documento insert(
            @PathVariable Long setorId,
            @PathVariable Long pastaId,
            @RequestBody Documento novo) {
        return service.insert(setorId, pastaId, novo);
    }

    @PutMapping
    @ApiOperation(value = "Atualiza um documento no  banco")
    public Documento update(
            @PathVariable Long setorId,
            @PathVariable Long pastaId,
            @RequestBody Documento documento) {
        return service.update(setorId, pastaId, documento);
    }
}
